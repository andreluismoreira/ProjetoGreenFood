package greenfood.exception;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidDTOValidator.class })
@Documented
public @interface ValidDTO {

    String message() default "(ifood.cadastro.Exception.ValidDTO.message)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
