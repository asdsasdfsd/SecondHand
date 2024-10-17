package iss.se.team4.secondhand.service;

import iss.se.team4.secondhand.common.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Result uploadFile(MultipartFile file);
}
