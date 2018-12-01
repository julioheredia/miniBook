package com.minibook.beans.util;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.minibook.beans.FormLogin;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

  public static final String PASSWORD_VALIDATE =
      "A senha deve ser a mesma que a senha de confirmação.";

  @Override
  public void validate(FacesContext context, UIComponent component, Object value)
      throws ValidatorException {

    String password = value.toString();

    UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmPassword");
    String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();

    if (password == null || password.isEmpty() || confirmPassword == null
        || confirmPassword.isEmpty()) {
      return;
    }

    if (!password.equals(confirmPassword)) {
      uiInputConfirmPassword.setValid(false);
      FormLogin.setMessageValidateForm(PASSWORD_VALIDATE);
    } else
      FormLogin.desabiliteMessageValidateForm();

  }
}
