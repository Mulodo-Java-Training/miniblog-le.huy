package uniTest.LayerTest.Service;

import static org.junit.Assert.*;
import mini.form.request.CommentCreateForm;
import mini.form.request.CommentEditForm;
import mini.form.request.PostCreateForm;
import mini.form.request.PostEditForm;
import mini.form.request.UserLoginForm;
import mini.model.Comments;
import mini.model.Posts;
import mini.model.Token;
import mini.service.CommentServiceInterface;
import mini.service.PostServiceInterface;
import mini.service.UserServiceInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class CommentServiceLayerTest
{

    @Autowired
    private UserServiceInterface user_service;

    private static Token token;

    private static Posts post;

    @Autowired
    private PostServiceInterface post_service;

    @Before
    public void setup()
    {

        UserLoginForm userlogin = new UserLoginForm();
        userlogin.username = "ledanghuy";
        userlogin.password = "abcd1234";
        token = user_service.loginUser(userlogin);
        PostCreateForm postcreate = new PostCreateForm();
        postcreate.title = "testposttitle";
        postcreate.content = "testpostcontent";

        int postid = post_service.createPost(postcreate.set_post_data(token.getUser()));
        assertTrue(postid > 0);
        post = post_service.getPostByPostId(postid);
    }

    @After
    public void teardown()
    {

        PostEditForm postedit = new PostEditForm();
        postedit.title = "testposttitle";
        postedit.content = "testpostcontent";
        postedit.id = post.getId();
        postedit.status = false;
        post_service.deletePost(postedit);
        user_service.logoutUser(token);
    }

    @Autowired
    private CommentServiceInterface comment_service;

    @Test
    public void CommentService_Create_Edit_Delete()
    {

        CommentCreateForm commentcreate = new CommentCreateForm();
        commentcreate.postid = post.getId();
        commentcreate.comment = "testcomment";

        try {
            comment_service.createComment(new Comments());
        } catch (Exception e) {
        }
        int commentid = comment_service.createComment(commentcreate.set_comment_data(
                token.getUser(), post));

        assertEquals(true, comment_service.checkCommentOwn(commentid, token.getUserId()));
        assertEquals(false, comment_service.checkCommentOwn(commentid, token.getUserId() + 1));

        CommentEditForm commentedit = new CommentEditForm();
        commentedit.id = commentid;
        commentedit.comment = "testcomment2";
        commentedit.postid = post.getId();

        assertEquals(true, comment_service.editComment(commentedit));
        assertEquals(false, comment_service.editComment(new CommentEditForm()));

        assertEquals(true, comment_service.deleteComment(commentedit));
        assertEquals(false, comment_service.editComment(new CommentEditForm()));

    }

    @Test
    public void CommentService_getcomment()
    {

        CommentCreateForm commentcreate = new CommentCreateForm();
        commentcreate.postid = post.getId();
        commentcreate.comment = "testcomment";
        int commentid = comment_service.createComment(commentcreate.set_comment_data(
                token.getUser(), post));

        int commentlist = comment_service.getAllCommentByPostId(post.getId()).size();
        assertTrue(commentlist > 0);

        commentlist = comment_service.getAllCommentByUserId(token.getUserId()).size();
        assertTrue(commentlist > 0);

        Comments comment = comment_service.getCommentById(commentid);
        assertEquals("testcomment", comment.getComment());
        CommentEditForm commentedit = new CommentEditForm();
        commentedit.id = commentid;
        commentedit.comment = "testcomment";
        commentedit.postid = post.getId();
        assertEquals(true, comment_service.deleteComment(commentedit));

    }

}
