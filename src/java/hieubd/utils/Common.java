/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.utils;

import hieubd.book.BookDTO;
import hieubd.book.BookErr;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author CND
 */
public class Common {

    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final String URL_SAVE_IMAGE = "/image/";

    public static void writeImage(HttpServletRequest request, String imageName, Part filePart) throws IOException, ServletException {
        InputStream fileContent = filePart.getInputStream();
        String path = request.getServletContext().getRealPath("/");
        FileOutputStream fos = new FileOutputStream(path + URL_SAVE_IMAGE + imageName, false);

        try {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = fileContent.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }

    }

    public static BookDTO checkValidInputBook(String bookId,
            String title,
            String image,
            String author,
            BookErr bookErr) {
        boolean valid = true;
        if (!bookId.matches("BS[0-9]{3}")) {
            valid = false;
            bookErr.setBookIdErr("Id invalid");
        }
        if (title.trim().length() < 5 || title.trim().length() > 50) {
            valid = false;
            bookErr.setTitleErr("Title must great than 5 and less than 50 character");
        }

        if (!image.trim().isEmpty() && !image.substring(image.lastIndexOf(".")).equalsIgnoreCase(".jpg")) {
            valid = false;
            bookErr.setImageErr("image invalid, please just upload the image have extension jpg");
        }

        if (author.trim().length() > 50 || author.trim().length() < 3) {
            valid = false;
            bookErr.setAuthorErr("Author must great than 3 and less than 50 character");
        }

        if (valid) {
            return new BookDTO(null, 0, 0, 0, title, image, null, author, 0, null);
        }

        return null;
    }
}
