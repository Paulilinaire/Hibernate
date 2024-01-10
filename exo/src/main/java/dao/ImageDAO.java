package dao;

import model.Image;

import java.util.List;

public interface ImageDAO {
        public void addImage(Image image, long id);

        public Image getImageById(long id);

}
