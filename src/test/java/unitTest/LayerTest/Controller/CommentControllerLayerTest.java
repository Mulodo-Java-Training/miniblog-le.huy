package unitTest.LayerTest.Controller;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import mini.controller.CommentController;
import mini.form.request.CommentCreateForm;
import mini.form.request.CommentEditForm;
import mini.systemvalue.SystemValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( "file:src/test/java/TestControllerContext.xml" )
public class CommentControllerLayerTest
{

    @Autowired
    private CommentController comment_controller;

    @Test
    public void CommentController_createComment_2008()
    {

        Response respone = comment_controller.createComment("token2008", null);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void CommentController_createComment_3005()
    {

        CommentCreateForm commentcreateform = new CommentCreateForm();
        commentcreateform.postid = 0;
        Response respone = comment_controller.createComment("validtoken",
                commentcreateform);
        assertEquals(SystemValue.ERRORCODE_3005, respone.getStatus());
    }

    @Test
    public void CommentController_createComment_4001()
    {

        CommentCreateForm commentcreateform = new CommentCreateForm();
        commentcreateform.postid = 1;
        Response respone = comment_controller.createComment("validtoken",
                commentcreateform);
        assertEquals(SystemValue.ERRORCODE_4001, respone.getStatus());
    }

    @Test
    public void CommentController_createComment_200()
    {

        CommentCreateForm commentcreateform = new CommentCreateForm();
        commentcreateform.comment = "comment200";
        commentcreateform.postid = 1;
        Response respone = comment_controller.createComment("validtoken",
                commentcreateform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void CommentController_editComment_2008()
    {

        Response respone = comment_controller.editComment("token2008", null);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void CommentController_editComment_4004()
    {

        CommentEditForm commenteditform = new CommentEditForm();
        commenteditform.id = 1;
        Response respone = comment_controller.editComment("validtoken",
                commenteditform);
        assertEquals(SystemValue.ERRORCODE_4004, respone.getStatus());
    }

    @Test
    public void CommentController_editComment_4002()
    {

        CommentEditForm commenteditform = new CommentEditForm();
        commenteditform.id = 2;
        Response respone = comment_controller.editComment("validtoken",
                commenteditform);
        assertEquals(SystemValue.ERRORCODE_4002, respone.getStatus());
    }

    @Test
    public void CommentController_editComment_200()
    {

        CommentEditForm commenteditform = new CommentEditForm();
        commenteditform.id = 3;
        Response respone = comment_controller.editComment("validtoken",
                commenteditform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void CommentController_deleteComment_2008()
    {

        Response respone = comment_controller.deleteComment("token2008", null);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void CommentController_deleteComment_4004()
    {

        CommentEditForm commenteditform = new CommentEditForm();
        commenteditform.id = 1;
        Response respone = comment_controller.deleteComment("validtoken",
                commenteditform);
        assertEquals(SystemValue.ERRORCODE_4004, respone.getStatus());
    }

    @Test
    public void CommentController_deleteComment_4003()
    {

        CommentEditForm commenteditform = new CommentEditForm();
        commenteditform.id = 2;
        Response respone = comment_controller.deleteComment("validtoken",
                commenteditform);
        assertEquals(SystemValue.ERRORCODE_4003, respone.getStatus());
    }

    @Test
    public void CommentController_deleteComment_200()
    {

        CommentEditForm commenteditform = new CommentEditForm();
        commenteditform.id = 3;
        Response respone = comment_controller.deleteComment("validtoken",
                commenteditform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_1001()
    {

        Response respone = comment_controller.getComment("validtoken",
                "invalidmode", 0);
        assertEquals(SystemValue.ERRORCODE_1001, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_2008()
    {

        Response respone = comment_controller.getComment("token2008", "id", 0);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_getid_4004()
    {

        Response respone = comment_controller.getComment("validtoken", "id", 0);
        assertEquals(SystemValue.ERRORCODE_4004, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_getid_200()
    {

        Response respone = comment_controller.getComment("validtoken", "id", 1);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_getcurrent_2009()
    {

        Response respone = comment_controller.getComment(
                "getuser2009invalidid", "current", 2);
        assertEquals(SystemValue.ERRORCODE_2009, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_getcurrent_200()
    {

        Response respone = comment_controller.getComment("validtoken",
                "current", 2);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_getpostid_3005()
    {

        Response respone = comment_controller.getComment("validtoken",
                "postid", 2);
        assertEquals(SystemValue.ERRORCODE_3005, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_getpostid_200()
    {

        Response respone = comment_controller.getComment("validtoken",
                "postid", 1);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_getuserid_2009()
    {

        Response respone = comment_controller.getComment("getuser2009",
                "userid", 2);
        assertEquals(SystemValue.ERRORCODE_2009, respone.getStatus());
    }

    @Test
    public void CommentController_getComment_getuserid_200()
    {

        Response respone = comment_controller.getComment("validtoken",
                "userid", 1);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }
}
