package uniTest.LayerTest.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mini.form.request.PostCreateForm;
import mini.form.request.PostEditForm;
import mini.form.request.UserLoginForm;
import mini.model.Posts;
import mini.model.Token;
import mini.service.PostServiceInterface;
import mini.service.UserServiceInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( "file:src/main/webapp/WEB-INF/applicationContext.xml" )
public class PostServiceLayerTest
{

    @Autowired
    private PostServiceInterface post_service;

    @Autowired
    private UserServiceInterface user_service;

    private static Token token;

    @Before
    public void setup()
    {

        UserLoginForm userlogin = new UserLoginForm();
        userlogin.username = "ledanghuy";
        userlogin.password = "abcd1234";
        token = user_service.loginUser(userlogin);
    }

    @After
    public void teardown()
    {

        user_service.logoutUser(token);
    }

    @Test
    public void PostController_Create_Edit_Delete_Post_service()
    {

        PostCreateForm postcreate = new PostCreateForm();
        postcreate.title = "testposttitle";
        postcreate.content = "testpostcontent";
        try
        {
            post_service.createPost(new Posts());
        } catch (Exception e)
        {
        }
        int postid = post_service.createPost(postcreate.set_post_data(token
                .getUser()));
        assertTrue(postid > 0);

        assertEquals(true, post_service.checkPostOwn(postid, token.getUserId()));
        assertEquals(false, post_service.checkPostOwn(5, token.getUserId()));
        assertEquals(false,
                post_service.checkPostOwn(postid + 1, token.getUserId()));

        PostEditForm postedit = new PostEditForm();
        postedit.title = "testposttitle2";
        postedit.content = "testpostcontent2";
        postedit.id = postid;
        postedit.status = false;
        assertEquals(true, post_service.editPost(postedit));
        assertEquals(true, post_service.editStatusPost(postedit));
        assertEquals(false, post_service.editStatusPost(new PostEditForm()));
        postedit.id = postid + 1;
        assertEquals(false, post_service.editPost(postedit));
        postedit.id = postid;
        assertEquals(true, post_service.deletePost(postedit));
        assertEquals(false, post_service.deletePost(new PostEditForm()));

    }

    @Test
    public void PostController_getpost()
    {

        PostCreateForm postcreate = new PostCreateForm();
        postcreate.title = "testposttitle";
        postcreate.content = "testpostcontent";

        int postid = post_service.createPost(postcreate.set_post_data(token
                .getUser()));
        assertTrue(postid > 0);

        int postlist = post_service.getAllPost().size();
        assertTrue(postlist > 0);
        postlist = post_service.getAllPostByUserId(token.getUserId()).size();
        assertTrue(postlist > 0);
        postlist = post_service.getTopPost(1).size();
        assertTrue(postlist > 0);
        postlist = post_service.searchPostByTitle("test").size();
        assertTrue(postlist > 0);
        Posts post = post_service.getPostByPostId(postid);
        assertEquals("testposttitle", post.getTitle());

        PostEditForm postedit = new PostEditForm();
        postedit.title = "testposttitle";
        postedit.content = "testpostcontent";
        postedit.id = postid;
        postedit.status = false;

        assertEquals(true, post_service.deletePost(postedit));

    }
}
