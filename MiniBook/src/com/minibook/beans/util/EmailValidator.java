package com.minibook.beans.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.minibook.beans.FormLogin;
import com.minibook.model.service.UsuarioService;
import com.minibook.model.service.impl.UsuarioServiceImpl;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

  private UsuarioService usuarioService;

  private static final String EMAIL_VALIDATE = "Você deve inserir um email valido.";
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
      + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";

  private Pattern pattern;
  private Matcher matcher;

  public EmailValidator() {
    pattern = Pattern.compile(EMAIL_PATTERN);
    usuarioService = new UsuarioServiceImpl();
  }


  @Override
  public void validate(FacesContext context, UIComponent component, Object value)
      throws ValidatorException {

    String email = value.toString();
    matcher = pattern.matcher(email);
    if (!matcher.matches()) {
      FormLogin.setMessageValidateForm(EMAIL_VALIDATE);

    } else {
      if (!usuarioService.validateEmail(email))
        FormLogin.setMessageValidateForm(EMAIL_VALIDATE);
      else
        FormLogin.desabiliteMessageValidateForm();
    }

  }

}
