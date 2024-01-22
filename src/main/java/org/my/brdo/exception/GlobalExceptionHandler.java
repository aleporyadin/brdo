    package org.my.brdo.exception;

    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.servlet.NoHandlerFoundException;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(NoHandlerFoundException.class)
        public String handleNoHandlerFoundException(NoHandlerFoundException ex, RedirectAttributes redirectAttributes) {
            return "redirect:/";
        }
    }
