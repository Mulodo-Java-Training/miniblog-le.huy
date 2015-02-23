package mini.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import mini.systemvalue.SystemValue;
import mini.util.ErrorCodeUtil;

import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.springframework.stereotype.Component;

/**
 * @author Le Dang Huy
 *
 */

@Provider
@Component
public class ValidatorAnnotationHandler implements
        ExceptionMapper<ResteasyViolationException>
{

    @Override
    public Response toResponse(ResteasyViolationException exception)
    {

        return Response
                .status(SystemValue.ERRORCODE_1001)
                .entity(ErrorCodeUtil
                        .set_error_code(SystemValue.ERRORCODE_1001)).build();
    }

}
