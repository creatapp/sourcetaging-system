package business.mission.picturesmanager.service.serviceimpl;

import business.common.entity.pictureentity.PictureEntity;
import business.mission.helper.Mission_PictureDbHelper;
import business.mission.picturesmanager.service.UploadPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class UploadPicturesImpl implements UploadPicturesService {

    @Autowired
    private Mission_PictureDbHelper missionPictureDbHelper;

    @Override
    public Long[] uploadPictures(String[] pictures, Long missionId) {

        /*
        * 注意：我们的这种设计方式无法恢复图片原来的名字。。。
        * 传输中一律使用我们提供的图片Id，只有迭代三再恢复了。。。
        * */

        //先解压成标准zip文件
        //decodeBase64ToZip(pictures,missionId.toString());

        //TODO 这里获取图片名字并和base64对应起来，，比如Map<name:base64>这样
        //然后得到每一张图片的base64
        //String[] base64PicStrArray = picToBase64(missionId.toString());
        String[] base64PicStrArray = pictures;
        Long[] names = new Long[base64PicStrArray.length];
        for (int i = 0; i < names.length ; i++)names[i] = missionId;//防止NPE

        //装载picture实体
        ArrayList<PictureEntity> pictureEntities = new ArrayList<>();
        for(int i = 0; i < base64PicStrArray.length; i++){
            PictureEntity pictureEntity = new PictureEntity(names[i],base64PicStrArray[i]);
            pictureEntities.add(pictureEntity);
        }

        //获取图片Id
        Long[] picIdArray = missionPictureDbHelper.add(pictureEntities);

        //修改图片名
//        try {
//            changePictureName(picIdArray,missionId);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return picIdArray;

    }

    public void changePictureName(Long[] picIdArray,Long missionId) throws IOException {
        //delete zip
        String path = getClass().getResource("/static").getPath()+ File.separator +"img"+File.separator+ missionId ;
//        File file=new File(path+ File.separator + "upload.zip");
//        file.delete();

        //change name
        File file=new File(path);
        String [] fileName = file.list();
        for(int i=0,j=0;i<fileName.length;i++){
            if(!fileName[i].endsWith(".zip")){
                File oldFile=new File(path+File.separator+fileName[i]);
                File newFile=new File(path+File.separator+picIdArray[j]+".jpg");
                if(!newFile.exists()){
                    newFile.createNewFile();
                }
                FileInputStream in=new FileInputStream(oldFile);
                FileOutputStream out=new FileOutputStream(newFile);
                int c;
                byte buffer[] =new byte[1024];
                while((c=in.read(buffer))!=-1){
                    for(int k=0;k<c;k++){
                        out.write(buffer[k]);
                    }
                }
                in.close();
                out.close();
                oldFile.deleteOnExit();
                j++;
            }
        }
    }
    public void decodeBase64ToZip(String base64ZipStr,String missionId){
        String path = getClass().getResource("/static").getPath();
        File stat=new File(path);
        if(!stat.exists()){
            stat.mkdir();
        }
        path = getClass().getResource("/static").getPath()+File.separator+"img";
        File img=new File(path);
        if(!img.exists()){
            img.mkdir();
        }
        try {
            File outFile = new File(path + File.separator + missionId);
            outFile.mkdir();
            outFile = new File(path + File.separator + missionId + File.separator + "upload.zip");
            outFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] bytes = base64ZipStr.getBytes();
            fos.write(Base64.getDecoder().decode(bytes));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(path + File.separator + missionId + File.separator + "upload.zip"));
            ZipEntry ze = zis.getNextEntry();

            while(ze  != null){
                FileOutputStream fos = new FileOutputStream(path + File.separator + missionId + File.separator + ze.getName());
                byte[] buffer = new byte[4096];

                int len;
                while ((len = zis.read(buffer)) !=  -1){
                    fos.write(buffer,0,len);
                }
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            zis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String[] picToBase64(String missionId){
        String path = getClass().getResource("/static").getPath()+File.separator+"img";
        File folder = new File(path + File.separator + missionId);
        File[] pics = folder.listFiles();

        int size = pics.length;
        String[] base64PicStr = new String[size-1];

        for (int i = 0,j=0; i < size; i++){
            File pic = pics[i];
            try {
                if(pics[i].getName().endsWith(".zip")){
                    continue;
                }
                FileInputStream fis = new FileInputStream(pic);
                byte[] bytes = new byte[(int)pic.length()];
                fis.read(bytes);


                byte[] base64Pic = Base64.getEncoder().encode(bytes);
                base64PicStr[j] = String.valueOf(base64Pic);
                j++;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return base64PicStr;
    }


}