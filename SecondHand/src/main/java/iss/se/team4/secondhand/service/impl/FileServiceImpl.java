package iss.se.team4.secondhand.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.service.FileService;
import iss.se.team4.secondhand.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileUploadUtils fileUploadUtils;

    // TODO 改成远程服务器地址或配置项，先以本地另一个服务为地址
    private static final String RESOURCE_SERVER_URL = "http://localhost:8080";
    // POST方法是上传，GET方法是获取当前目录下所有图片，DELETE方法是删除指定key对应的图片
    private static final String PATH = "/oss/pictures";

    @Override
    public Result uploadFile(MultipartFile file) {
        String url = RESOURCE_SERVER_URL + PATH;
        try {
            String result = fileUploadUtils.sendMultipartFile(url, file);
            // upload success
            if (result != null) {
                JSONObject jsonObject = JSONUtil.parseObj(result);
                String data = jsonObject.get("data").toString();
                String key = JSONUtil.parseObj(data).get("picUrl").toString();
                return Result.success(key);
            }
        } catch (Exception e) {
            return Result.failure("upload fail: "+ e.getMessage());
        }

        return Result.failure("upload fail");
    }
}
