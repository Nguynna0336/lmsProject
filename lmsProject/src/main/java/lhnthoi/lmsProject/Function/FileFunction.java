package lhnthoi.lmsProject.Function;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FileFunction {

    public String checkRequire(List<MultipartFile> files, List<String> requiredType, Long maxSizeEachFile, int maxSize) {
        if(files == null)
        {return "No file uploaded! Please upload a file.";}
        if(files.size() > maxSize)
        {return "Can not upload more than " + maxSize +" files";}
        for(MultipartFile file : files) {
            if(file.getContentType() == null || checkType(file, requiredType))
                {return "File can be " + requiredType.stream().map(Object::toString).collect(Collectors.joining(", "));}
            if(file.getSize() >  maxSizeEachFile *1024*1024)
                {return "File is too large! Maximum size is "+ maxSizeEachFile +"MB";}
            ///co cach nao de toi uu khuc nay k nhi? may cai if nhin dan don vc
        }
        return "accepted";
    }
    public String storeFile(MultipartFile file, String purpose) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = UUID.randomUUID() + "_" + fileName;
        Path uploadDir = Paths.get(purpose);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return destination.toString();
    }
    private boolean checkType(MultipartFile file, List<String> required) {
        String fileName = file.getName();
        String fileType = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") +1);
        return file.getContentType()!= null && required.contains(fileType);
    }
}
