package spirngftpuploadtest.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@Slf4j
public class uploadfile {

    @Autowired
    private FTPClient con;

    @PostMapping("/file")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
                boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());

                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message",
                    "Could not upload " + file.getOriginalFilename() + "!");
        }

        return "redirect:/";
    }

}
