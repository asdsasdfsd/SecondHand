package iss.se.team4.secondhand.controller;

import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result uploadPic(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
