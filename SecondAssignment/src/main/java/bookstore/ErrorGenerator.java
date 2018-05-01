package bookstore;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class ErrorGenerator {
        public static List<String> getErrorsFromBinding(BindingResult bindingResult)
        {
            List<String> result =new ArrayList<>();
            bindingResult.getAllErrors().forEach(error->result.add(error.getDefaultMessage()));
            return result;
        }
}
