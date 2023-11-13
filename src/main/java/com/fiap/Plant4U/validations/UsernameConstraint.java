package com.fiap.Plant4U.validations;

import com.fiap.Plant4U.validations.impl.UsernameConstraintImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameConstraintImpl.class)//Classe que contém as validações
@Target({ElementType.METHOD, ElementType.FIELD})//quais os campos que serão aceitos na anotação
@Retention(RetentionPolicy.RUNTIME)//Validação em tempo de execução
public @interface UsernameConstraint {

    String message() default "Invalid username";//mensagem que vai aparecer quando a anotação for chamada

    Class<?>[] groups() default{};//definição dos grupos que vão aceitar a anotação

    Class<? extends Payload>[] payload() default{};//Payload: O nível em que o erro vai ocorrer
}
