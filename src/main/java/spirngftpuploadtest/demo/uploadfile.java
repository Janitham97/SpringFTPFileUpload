package spirngftpuploadtest.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.SocketException;

@RestController
@Slf4j
public class uploadfile {

    @Autowired
    private FTPClient con;

    @PostMapping("/file")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

//        String FTP_ADDRESS = "localhost";
//        String LOGIN = "anonymous";
//        String PSW = "";
//


        try {


//            if (con.login(LOGIN, PSW)) {

                boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());

                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");
//            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message",
                    "Could not upload " + file.getOriginalFilename() + "!");
        }

        return "redirect:/";
    }

//    public static FTPSClient getFTPConnection() {
//        FTPSClient ftp = new FTPSClient();
//        int reply;
//        try {
//            ftp.connect("localhost");
//            ftp.login("anonymous", "");
//            reply = ftp.getReplyCode();
//            if (FTPReply.isPositiveCompletion(reply)) {
//                for (int i = 0; i < ftp.listDirectories().length; i++) {
//                    System.out.println(ftp.listDirectories());
//                }
//                ftp.changeWorkingDirectory("user");
//                showServerReply(ftp);
//                //  LOGGER.info("-------->>> %s", ftp.printWorkingDirectory());
//                showServerReply(ftp);
//                //LOGGER.info("Connected Success");
//            } else {
//                //LOGGER.error("Connection Failed");
//                ftp.disconnect();
//                ftp = null;
//            }
//        } catch (SocketException ex) {
//            //LOGGER.error("Error while trying to connect to the ImageBak Repository");
//            ex.printStackTrace();
//            ftp = null;
//        } catch (IOException ex) {
//            //LOGGER.error("Error while trying to connect to the ImageBak Repository");
//            ex.printStackTrace();
//            ftp = null;
//        }
//
//        return ftp;
//
//    }
//    private static void showServerReply(FTPClient ftpClient) {
//        String[] replies = ftpClient.getReplyStrings();
//        if (replies != null && replies.length > 0) {
//            for (String aReply : replies) {
//                System.out.println("SERVER: " + aReply);
//            }
//        }
//    }

}
