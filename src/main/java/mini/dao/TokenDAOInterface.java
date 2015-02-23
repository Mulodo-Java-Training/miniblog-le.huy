package mini.dao;

import mini.model.Token;

/**
 * @author Le Dang Huy
 *
 */

public interface TokenDAOInterface
{

    public void save(Token token);

    // public void persist(Token token);

    public void update(Token token);

    // public void saveOrUpdate(Token token);

    public void delete(Token token);

    // public Token get(int id);

    // public Token load(int id);

    // custom function
    public Token getTokenByAccessToken(String access_token);

    // public List<token> getTokenByUserId(int user_id);
    public void clearTokenByUserId(int user_id);
}
