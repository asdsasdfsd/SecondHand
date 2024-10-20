package top.snailclimb.service;

import com.aliyun.oss.model.OSSObjectSummary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.snailclimb.common.CommonResult;
import top.snailclimb.config.AliyunOSSConfig;
import top.snailclimb.dto.PicDto;
import top.snailclimb.utils.AliyunOssUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shuang.kou
 */
@Service
public class AliyunOssPicStorageService {
    private final AliyunOssUtil aliyunOssUtil;
    private final AliyunOSSConfig aliyunOssConfig;

    public AliyunOssPicStorageService(AliyunOssUtil aliyunOssUtil, AliyunOSSConfig aliyunOssConfig) {
        this.aliyunOssUtil = aliyunOssUtil;
        this.aliyunOssConfig = aliyunOssConfig;
    }

    public CommonResult handleUploadImage(MultipartFile multipartFile) {

        if (!isImageByMultipartFile(multipartFile)) {
            return CommonResult.builder().code(1).msg("upload failed, wrong file type").build();
        }
        String fileName = multipartFile.getOriginalFilename().replaceAll(" +", "");
        String key = getKey(fileName);
        String picUrl = getPicUrl(key);
        try {
            aliyunOssUtil.upLoadFile(key, multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.builder().code(1).msg("upload failed").build();
        }
        return CommonResult.builder().code(0).data(new PicDto(picUrl, key)).msg("upload success").build();
    }

    /**
     * List all the images in the current directory
     */
    public CommonResult listPicturesByFileHost(String fileHost) {
        List<OSSObjectSummary> ossObjectSummaries = aliyunOssUtil.listFilesByFileHost(fileHost);
        List<PicDto> picDtos = new ArrayList<>();
        // 过滤出来所有图片地址
        ossObjectSummaries.forEach(ossObjectSummary -> {
            String key = ossObjectSummary.getKey();
            if (isImageByFileKey(key)) {
                picDtos.add(new PicDto(getPicUrl(key), key));
            }
        });
        return CommonResult.builder().code(0).data(picDtos).msg("Query all pictures in the current directory successfully!").build();

    }

    public void deletePicByKey(String key) {
        aliyunOssUtil.deleteFileByKey(key);
    }

    private String getPicUrl(String key) {
        return "https://" + aliyunOssConfig.getBucketName() + "." + aliyunOssConfig.getEndpoint() + "/" + key;
    }

    private String getKey(String fileName) {
        LocalDateTime dt = LocalDateTime.now();
        LocalDate date = dt.toLocalDate();
        return aliyunOssConfig.getFileHost() + "/" + (date + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + fileName);
    }

    private static boolean isImageByFileKey(String key) {
        String picRegex = "([^\\s]+(\\.(?i)(/bmp|jpg|gif|png))$)";
        Pattern pattern = Pattern.compile(picRegex);
        Matcher matcher = pattern.matcher(key);
        return matcher.matches();
    }

    private static boolean isImageByMultipartFile(MultipartFile multipartFile) {
        return !multipartFile.isEmpty() && multipartFile.getContentType().startsWith("image");
    }


}
