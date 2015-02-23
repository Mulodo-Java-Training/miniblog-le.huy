package mini.service;

import java.util.Calendar;
import java.util.List;

import mini.dao.TokenDAOInterface;
import mini.dao.UserDAOInterface;
import mini.form.request.UserChangePasswordForm;
import mini.form.request.UserLoginForm;
import mini.form.request.UserUpdateForm;
import mini.model.Token;
import mini.model.Users;
import mini.util.HashGenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Le Dang Huy
 *
 */

@Service
public class UserService implements UserServiceInterface
{

    @Autowired
    private UserDAOInterface user_DAO;

    @Autowired
    private TokenDAOInterface token_DAO;

    @Autowired
    private TokenServiceInterface token_service;

    @Override
    @Transactional
    public boolean checkUserExist(String username)
    {

        try
        {
            if (user_DAO.getUserByUsername(username) != null)
                return true;
            else
            {
                return false;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean checkEmailExist(String email)
    {

        try
        {
            if (user_DAO.getUserByEmail(email) != null)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean insertUser(Users user)
    {

        try
        {
            user_DAO.save(user);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Token loginUser(UserLoginForm data)
    {

        Users user;
        try
        {
            user = user_DAO.getUserByUsername(data.username);

            if (user == null
                    || user.getPassword().compareTo(
                            HashGenUtil.Encrypt_password(data.password)) != 0)
            {
                return null;
            } else
            {

                return token_service.createToken(user);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean logoutUser(Token token)
    {

        try
        {
            token_DAO.delete(token);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateUserInfo(UserUpdateForm data, int user_id)
    {

        try
        {
            Users user = user_DAO.get(user_id);

            if (user == null
                    || user.getPassword().compareTo(
                            HashGenUtil.Encrypt_password(data.password)) != 0)
            {
                return false;
            } else
            {
                user.setFirstname(data.firstname);
                user.setLastname(data.lastname);
                user.setModified_at(Calendar.getInstance().getTime());
                user_DAO.update(user);
                return true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Users getUserById(int id)
    {

        try
        {
            return user_DAO.get(id);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Token changePassword(UserChangePasswordForm data, int user_id)
    {

        try
        {
            Users user = user_DAO.get(user_id);
            if (user.getPassword().compareTo(
                    HashGenUtil.Encrypt_password(data.old_password)) != 0)
            {
                return null;
            }
            user.setPassword(HashGenUtil.Encrypt_password(data.new_password));
            token_service.clearTokenTalbeByUserId(user.getId());
            user_DAO.update(user);
            return token_service.createToken(user);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings ( "rawtypes" )
    @Override
    @Transactional
    public List searchUserByUsername(String query)
    {

        try
        {
            return user_DAO.searchUserByUsername(query);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteUser(String username)
    {

        try
        {
            Users user = user_DAO.getUserByUsername(username);
            if (user != null)
            {
                user_DAO.delete(user);
            }
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Users getUserByUsername(String username)
    {

        try
        {
            return user_DAO.getUserByUsername(username);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
