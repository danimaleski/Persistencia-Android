package com.example.udesc.persistencia_application.validator;

import com.example.udesc.persistencia_application.model.SuperUser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Validator {
    private ArrayList<SuperUser> list = new ArrayList<>();

    public Validator(InputStream is) {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        try {
            String linha = br.readLine();
            while (linha != null) {
                String[] split = linha.split(",");

                SuperUser user = new SuperUser();
                user.setNome(split[0]);
                user.setSenha(split[1]);
                list.add(user);
                linha = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<SuperUser> getList() {
        return list;
    }

    public boolean validateCredentials(String user, String senha) {
        for (int i = 0; i < list.size(); i++) {
            SuperUser usuario = list.get(i);

            if (usuario.getSenha().equals(senha) && usuario.getNome().equals(user)) {
                return true;
            }
        }

        return false;
    }

    public boolean validateCredentials(SuperUser user) {
        return validateCredentials(user.getNome(), user.getSenha());
    }

    public void criarArquivo(File dir, ArrayList<SuperUser> lista) {
        File file = new File(dir, "user.csv");
        FileWriter writer;
        try {
            writer = new FileWriter(file, true);

            for (int i = 0; i < lista.size(); i++) {
                SuperUser usuario = lista.get(i);
                writer.append(usuario.getNome() + "," + usuario.getSenha() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
