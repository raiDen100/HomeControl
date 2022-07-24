package com.raiden.homecontrol.services;

import com.sourceforge.snap7.moka7.S7;
import com.sourceforge.snap7.moka7.S7Client;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlcService {
    @Autowired
    private final S7Client client;

    public PlcService(S7Client client) {
        this.client = client;
    }

    public synchronized void setMerker(int pos, int bit, boolean flag){
        byte[] localBuffer = new byte[1];
        S7.SetBitAt(localBuffer, 0, bit, flag);

        client.WriteArea(S7.S7AreaMK, 0, pos, 1, localBuffer);
    }

    public synchronized void setDatabaseBit(int dbNumber, int pos, boolean flag){
        byte[] localBuffer = new byte[1];
        S7.SetBitAt(localBuffer, 0, 1, flag);

        client.WriteArea(S7.S7AreaDB, dbNumber, pos, 1, localBuffer);
    }

    public synchronized Float getFloatAt(int dbNumber, int pos){
        byte[] localBuffer = new byte[4];
        client.ReadArea(S7.S7AreaDB, dbNumber, pos, 4, localBuffer);

        return S7.GetFloatAt(localBuffer, 0);
    }

    public synchronized Integer getWordAt(int dbNumber, int pos){
        byte[] localBuffer = new byte[2];
        client.ReadArea(S7.S7AreaDB, dbNumber, pos, 2, localBuffer);

        return S7.GetWordAt(localBuffer, 0);
    }


    public synchronized JSONObject getPositions() {
        int size = 23;
        byte[] localBuffer = new byte[size+1];
        client.ReadArea(S7.S7AreaDB, 200, 0, size+1, localBuffer);

        List<Integer> positions = new ArrayList<>();
        for(int i = 0; i < size; i+=2){
            int position = S7.GetWordAt(localBuffer, i);
            positions.add(position);
        }

        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < positions.size(); i++)
            jsonObject.put("r" + (i+1), positions.get(i));

        return jsonObject;

    }
}
