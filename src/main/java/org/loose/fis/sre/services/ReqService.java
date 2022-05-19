package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.ClasaAlreadyExistsException;
import org.loose.fis.sre.exceptions.ReqAlreadyExistsException;
import org.loose.fis.sre.model.Classes;
import org.loose.fis.sre.model.Requests;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ReqService {
    private static ObjectRepository<Requests> reqRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("requests11.db").toFile())
                .openOrCreate("req", "req");

        reqRepository = database.getRepository(Requests.class);
    }

    private static int maxId (){
        int nr =0;
        for (Requests req : reqRepository.find()) {
            nr++;

        }
        return nr;
    }


    public static void addStatus(String reqString,String status)
    {
        for (Requests req : reqRepository.find()) {
            if (Objects.equals(req.toString(),reqString))
            {

                req.setStatus(status);
                reqRepository.update(req);
            }
        }

    }


    public static void addReq(String username,String clasa) throws ReqAlreadyExistsException {
        checkReqDoesNotAlreadyExist(username,clasa );
        int nr = maxId();
        reqRepository.insert(new Requests(nr,username , clasa));
    }


    private static void checkReqDoesNotAlreadyExist(String username , String clasa) throws ReqAlreadyExistsException {
        for (Requests req : reqRepository.find()) {
            if (Objects.equals(username, req.getUsername()) && Objects.equals(clasa,req.getClasa().toString()) )
                throw new ReqAlreadyExistsException();
        }
    }



    public static List<Requests> getReqList()  {
        List<Requests> tempList =new ArrayList<>();

        for (Requests req : reqRepository.find()) {
            tempList.add(req);
        }


        return tempList;
    }

    public static List<Requests> getReqWithStatusList(String username)  {
        List<Requests> tempList =new ArrayList<>();

        for (Requests req : reqRepository.find()) {
            if (Objects.equals(req.getUsername(),username)){
                tempList.add(req);
            }
        }


        return tempList;
    }






}
