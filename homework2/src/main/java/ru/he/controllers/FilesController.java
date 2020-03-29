package ru.he.controllers;

import ru.he.model.FileInfo;
import ru.he.services.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class FilesController {

    @Autowired
    private FileInfoService fileInfoService;

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public ModelAndView uploadFileView(@RequestParam("file") MultipartFile multipartFile, HttpSession session) {
        String email = (String) session.getAttribute("user-email");
        System.out.println("Email from current session: " + email);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setMultipartFile(multipartFile);
        fileInfo.setEmail(email);

        fileInfoService.save(fileInfo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("file_url", fileInfo.getStorageFileName());
        modelAndView.setViewName("ftl/fileUploaded");
        return modelAndView;
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public ModelAndView uploadFile() {
        return new ModelAndView("html/fileUpload");
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> saveFile(@PathVariable String filename) {
        FileInfo fileInfo = fileInfoService.getFileInfoByStorageFilename(filename);
        Resource resource = fileInfoService.getResourceByFileInfo(fileInfo);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getOriginalFileName() + "\"")
                .body(resource);
    }
}
