package dev.bread.springboot.core.api.controller.v1.request;

import dev.bread.springboot.core.api.domain.ExampleData;

public record ExampleRequestDto(String data) {
    public ExampleData toExampleData() {
        return new ExampleData(data, data);
    }
}
