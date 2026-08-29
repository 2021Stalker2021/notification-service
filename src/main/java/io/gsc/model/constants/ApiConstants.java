package io.gsc.model.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConstants {
    public static final String SUBJECT_WELCOME = "Добро пожаловать!";
    public static final String SUBJECT_DELETE = "Аккаунт удален";

    public static final String MSG_WELCOME = "Здравствуйте! Ваш аккаунт на сайте успешно создан.";
    public static final String MSG_DELETE = "Здравствуйте! Ваш аккаунт был удалён.";

    public static final String EMAIL_FROM = "no-reply@yourwebsite.com";
}
