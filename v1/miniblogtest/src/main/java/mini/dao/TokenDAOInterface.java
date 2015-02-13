package mini.dao;

import mini.model.token;

public interface TokenDAOInterface {
    public void save(token token);
    public void persist(token token);
    public void update(token token);
    public void saveOrUpdate(token token);
    public void delete(token token);

    public token get(int id);
    public token load(int id);

    //custom function
    public token getTokenByAccessToken(String access_token);
//    public List<token> getTokenByUserId(int user_id);
    public void clearTokenByUserId(int user_id);
}
