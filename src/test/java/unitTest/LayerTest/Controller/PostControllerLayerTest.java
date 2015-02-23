package unitTest.LayerTest.Controller;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import mini.controller.PostController;
import mini.form.request.PostCreateForm;
import mini.form.request.PostEditForm;
import mini.systemvalue.SystemValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/java/TestControllerContext.xml")
public class PostControllerLayerTest
{

    @Autowired
    private PostController post_controller;

    @Test
    public void PostController_createPost_2008()
    {

        Response respone = post_controller.createPost("token2008", null);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void PostController_createPost_3001()
    {

        PostCreateForm postcreateform = new PostCreateForm();
        postcreateform.title = "createpost3001";
        postcreateform.content = "createpost3001";
        Response respone = post_controller.createPost("validtoken", postcreateform);
        assertEquals(SystemValue.ERRORCODE_3001, respone.getStatus());
    }

    @Test
    public void PostController_createPost_200()
    {

        PostCreateForm postcreateform = new PostCreateForm();
        postcreateform.title = "createpost200";
        postcreateform.content = "createpost200";
        Response respone = post_controller.createPost("validtoken", postcreateform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_editPost_2008()
    {

        Response respone = post_controller.editPost("token2008", null);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void PostController_editPost_3005()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 1;
        Response respone = post_controller.editPost("validtoken", posteditform);
        assertEquals(SystemValue.ERRORCODE_3005, respone.getStatus());
    }

    @Test
    public void PostController_editPost_3003()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 2;
        Response respone = post_controller.editPost("validtoken", posteditform);
        assertEquals(SystemValue.ERRORCODE_3003, respone.getStatus());
    }

    @Test
    public void PostController_editPost_200()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 3;
        Response respone = post_controller.editPost("validtoken", posteditform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_changePostStatus_2008()
    {

        Response respone = post_controller.changePostStatus("token2008", null);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void PostController_changePostStatus_3005()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 1;
        Response respone = post_controller.changePostStatus("validtoken", posteditform);
        assertEquals(SystemValue.ERRORCODE_3005, respone.getStatus());
    }

    @Test
    public void PostController_changePostStatus_3002()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 2;
        Response respone = post_controller.changePostStatus("validtoken", posteditform);
        assertEquals(SystemValue.ERRORCODE_3002, respone.getStatus());
    }

    @Test
    public void PostController_changePostStatus_200()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 3;
        Response respone = post_controller.changePostStatus("validtoken", posteditform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_deletePost_2008()
    {

        Response respone = post_controller.deletePost("token2008", null);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void PostController_deletePost_3005()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 1;
        Response respone = post_controller.deletePost("validtoken", posteditform);
        assertEquals(SystemValue.ERRORCODE_3005, respone.getStatus());
    }

    @Test
    public void PostController_deletePost_3004()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 2;
        Response respone = post_controller.deletePost("validtoken", posteditform);
        assertEquals(SystemValue.ERRORCODE_3004, respone.getStatus());
    }

    @Test
    public void PostController_deletePost_200()
    {

        PostEditForm posteditform = new PostEditForm();
        posteditform.id = 3;
        Response respone = post_controller.deletePost("validtoken", posteditform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_getPost_1001()
    {

        Response respone = post_controller.getPost("validtoken", "invalidmode", 0, 0, "s");
        assertEquals(SystemValue.ERRORCODE_1001, respone.getStatus());
    }

    @Test
    public void PostController_getPost_2008()
    {

        Response respone = post_controller.getPost("token2008", "all", 0, 0, "s");
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getall_200()
    {

        Response respone = post_controller.getPost("validtoken", "all", 0, 0, null);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getcurrent_2009()
    {

        Response respone = post_controller.getPost("getuser2009", "current", 0, 0, null);
        assertEquals(SystemValue.ERRORCODE_2009, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getcurrent_200()
    {

        Response respone = post_controller.getPost("validtoken", "current", 0, 0, null);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getuserid_2009()
    {

        Response respone = post_controller.getPost("getuser2009", "userid", 0, 0, null);
        assertEquals(SystemValue.ERRORCODE_2009, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getuserid_200()
    {

        Response respone = post_controller.getPost("validtoken", "userid", 1, 0, null);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getpostid_3005()
    {

        Response respone = post_controller.getPost("validtoken", "postid", 0, 0, null);
        assertEquals(SystemValue.ERRORCODE_3005, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getpostid_200()
    {

        Response respone = post_controller.getPost("validtoken", "postid", 1, 0, null);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getposttop_200()
    {

        Response respone = post_controller.getPost("validtoken", "top", 1, 0, null);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void PostController_getPost_getposttile_200()
    {

        Response respone = post_controller.getPost("validtoken", "title", 1, 0, null);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }
}
