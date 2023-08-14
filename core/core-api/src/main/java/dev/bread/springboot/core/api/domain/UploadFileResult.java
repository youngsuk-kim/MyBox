package dev.bread.springboot.core.api.domain;

import java.time.LocalDateTime;

public record UploadFileResult(String fileName, LocalDateTime expiredAt) {
}
