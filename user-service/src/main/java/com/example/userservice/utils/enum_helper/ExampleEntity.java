package com.example.userservice.utils.enum_helper;

import javax.persistence.Convert;

public class ExampleEntity {

    @Convert(converter = ExampleEnum.Converter.class)
    private ExampleEnum exampleEnum;
}
