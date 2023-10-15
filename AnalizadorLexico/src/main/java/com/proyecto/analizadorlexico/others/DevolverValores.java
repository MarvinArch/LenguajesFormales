/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.analizadorlexico.others;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author david
 */
public class DevolverValores {
    
    public static String unirValores(String val) {
        List<String> temporal=new ArrayList<>();
        String valor = val;
        String Resultado="";
        int num=0;
        if (!valor.contains("[") && !valor.contains("{")) {
            List<String> valorfinal = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(valor, "+");// busca los signos + y divide el contenido en arreglo
            while (st.hasMoreTokens()) {
                String temp = st.nextToken();
                if (!temp.equals("+")) {
                    valorfinal.add(temp);
                }
            }
            for (int i = valorfinal.size() - 1; i >= 0; i--) {//se recorre arreglo para unir Strings y ejecutar operaciones
                String rp = "";
                if (valorfinal.get(i).contains("\"")) {
                    temporal.add(valorfinal.get(i));
                } else {
                    if (comprobarMasOperaciones(valorfinal.get(i))) {
                        rp = cantidades(InvertirOperaciones(valorfinal.get(i)));
                        temporal.add(rp);
                    } else if (comprobarComparacion(valorfinal.get(i))) {
                        rp = "\""+resultadoComparacion(valorfinal.get(i))+"\"";
                        temporal.add(rp);
                    } else {
                        temporal.add(valorfinal.get(i));
                    }

                }
            }
            boolean valores = false;
            double valoragre = 0;
            for (int i = 0; i < temporal.size(); i++) {
                if (temporal.get(i).contains("\"")) {
                    if (valores) {
                        String compro = "" + valoragre;
                        if (entero(compro)) {
                            compro = compro.replace(".0", "");
                            int nuw = Integer.parseInt(compro);
                            Resultado += "" + nuw;
                        } else {
                            Resultado += "" + valoragre;
                        }
                        valores = false;
                    }
                    Resultado += temporal.get(i).replaceAll("\"", "");
                } else {
                    try {
                        double tempo = Double.parseDouble(temporal.get(i));
                        if (!valores) {
                            valoragre = tempo;
                            valores = true;
                        } else {
                            valoragre += tempo;
                        }
                    } catch (Exception e) {
                        System.out.println("El error se da en la partir de la linea 56 clase Devolver Valores" + e);
                    }
                }
                if (i == temporal.size() - 1 && valores) {
                    String compro = "" + valoragre;
                    if (entero(compro)) {
                        try {
                            compro = compro.replace(".0", "");
                            int nuw = Integer.parseInt(compro);
                            Resultado += "" + nuw;
                        } catch (Exception e) {
                            compro = compro.replace(".0", "");
                            Resultado += "" + valoragre;
                        }
                        
                    } else {
                        Resultado += "" + valoragre;
                    }
                }
            }
        }
        
        return Resultado;
    }
    
    private static String resultadoComparacion(String val1){
        Mapas map = new Mapas();
        List<String> valorfinal;
        int temp1=-1;
        int temp2=-1;
        String response="";
        if (val1.contains("<=")) {
            valorfinal=separarComparaciones(val1,"<=");
            val1= val1.replace("<=", "");
            try{
                temp1=Integer.parseInt(valorfinal.get(0));
                temp2=Integer.parseInt(valorfinal.get(1));
            }catch(Exception e){}
            if (temp1<=temp2) {
                response="True";
            }else{
                response="False";
            }
        }else if (val1.contains(">=")) {
            valorfinal=separarComparaciones(val1,">=");
            val1= val1.replace(">=", "");
            try{
                temp1=Integer.parseInt(valorfinal.get(0));
                temp2=Integer.parseInt(valorfinal.get(1));
            }catch(Exception e){}
            if (temp1>=temp2) {
                response="True";
            }else{
                response="False";
            }
        } else if (val1.contains("==")) {
            valorfinal=separarComparaciones(val1,"==");
            val1= val1.replace("==", "");
            try{
                temp1=Integer.parseInt(valorfinal.get(0));
                temp2=Integer.parseInt(valorfinal.get(1));
            }catch(Exception e){}
            if (temp1==temp2) {
                response="True";
            }else{
                response="False";
            }
        } else if (val1.contains("!=")) {
            valorfinal=separarComparaciones(val1,"!=");
            val1= val1.replace("!=", "");
            try{
                temp1=Integer.parseInt(valorfinal.get(0));
                temp2=Integer.parseInt(valorfinal.get(1));
            }catch(Exception e){}
            if (temp1!=temp2) {
                response="True";
            }else{
                response="False";
            }
        } else if (val1.contains("<")) {
            valorfinal=separarComparaciones(val1,"<");
            val1= val1.replace("<", "");
            try{
                temp1=Integer.parseInt(valorfinal.get(0));
                temp2=Integer.parseInt(valorfinal.get(1));
            }catch(Exception e){}
            if (temp1<temp2) {
                response="True";
            }else{
                response="False";
            }
        }else if (val1.contains(">")) {
            valorfinal=separarComparaciones(val1,">");
            val1= val1.replace(">", "");
            try{
                temp1=Integer.parseInt(valorfinal.get(0));
                temp2=Integer.parseInt(valorfinal.get(1));
            }catch(Exception e){}
            if (temp1>temp2) {
                response="True";
            }else{
                response="False";
            }
        }
        return response;
    }
    
    private static List<String> separarComparaciones(String val1, String sepa){
        List<String> valorfinal = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(val1, sepa);
        while (st.hasMoreTokens()) {
            valorfinal.add(st.nextToken());
        }
        return valorfinal;
    }
    private static boolean comprobarComparacion(String val1){
        
        if (val1.contains("<=")||val1.contains("<=")||val1.contains(">=") || val1.contains("==") || val1.contains("!=") ||
                val1.contains("<") || val1.contains(">")) {
            return true;
        }
        return false;
    }
    
    private static String cantidades(String val1) {
        System.out.println("recivio"+val1+" en la linea 91"); 
        String resp = "";
        boolean convertido = false;

        if (val1.contains("**")) {
            val1 = Potencia(val1);
        } else if (val1.contains("/")) {
            val1 = Division(val1);
        } else if (val1.contains("*")) {
            val1 = Multiplicacion(val1);
        } else if (val1.contains("-")) {
            val1 = Resta(val1);
        }

        try {   
            resp =""+ Double.parseDouble(val1);
            convertido = true;
            
        } catch (Exception e) {
            convertido = false;
        }
        if (!convertido) {
            resp = cantidades(val1);
        }
        return resp;
    }

    private static boolean comprobarMasOperaciones(String val1){
        if (val1.contains("+") || val1.contains("-") || val1.contains("*") || val1.contains("/")) {
            return true;
        }
        return false;
    }
    
    private static boolean entero(String val){
        int next=-1;
        List<String> valorfinal = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(val, ".");
        while (st.hasMoreTokens()) {
            valorfinal.add(st.nextToken());
        }
        try {
            next=Integer.parseInt(valorfinal.get(1));
        } catch (Exception e) {
        }
        if (next>0) {
            return false;
        }
        return true;
    }
    
    private static String InvertirOperaciones(String val1){
        String temp5="";
        List<String> temp4= new ArrayList<>();
        char[] invert= val1.toCharArray();
        for (int i = 0; i < invert.length; i++) {
            if (invert[i]!='-' && invert[i]!='*' && invert[i]!='/') {
                temp5+=invert[i];
            }else{
                temp4.add(temp5);
                temp4.add(""+invert[i]);
                temp5="";
            }
            if (i==invert.length-1) {
                temp4.add(temp5);
                temp5="";
            }
            
        }
        
        for (int i = temp4.size()-1; i >=0 ; i--) {
            temp5+=temp4.get(i);
        }
        return temp5;
    }
    
    private static String Potencia(String val1){
        String ultimo="";
        String primeto="";
        if (val1.contains("**")) {
            val1= val1.replace("**", "&");
            List<String> valorfinal = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(val1, "&");
            while (st.hasMoreTokens()) {
                valorfinal.add(st.nextToken());
            }
            
            ultimo=valorfinal.get(0);
            primeto=valorfinal.get(1);
            if (ultimo.contains("/")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(ultimo, "/");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                ultimo=valortemp2.get(valortemp2.size()-1);//4
            }
            if (primeto.contains("/")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(primeto, "/");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                primeto=valortemp2.get(0);
            }
            if (ultimo.contains("*")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(ultimo, "*");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                ultimo=valortemp2.get(valortemp2.size()-1);
            }
            if (primeto.contains("-")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(primeto, "*");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                primeto=valortemp2.get(0);
            }
            
            
            if (ultimo.contains("-")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(ultimo, "-");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                ultimo=valortemp2.get(valortemp2.size()-1);
            }
            if (primeto.contains("-")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(primeto, "-");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                primeto=valortemp2.get(0);
            }
            double resp= (Math.pow(Double.parseDouble(ultimo), Double.parseDouble(primeto)));
            val1=val1.replace(ultimo+"&"+primeto, ""+resp);
            
            if (val1.contains("&")) {
                val1=Potencia(val1);
            }
        }
        return val1;
    }
     
    private static String Division(String val1){
        String ultimo="";
        String primeto="";
        if (val1.contains("/")) {
            List<String> valorfinal = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(val1, "/");
            while (st.hasMoreTokens()) {
                valorfinal.add(st.nextToken());
            }
            for (int i = 0; i <2; i++) {
                if (valorfinal.get(i).contains("*")) {
                    List<String> valortemp = new ArrayList<>();
                    st = new StringTokenizer(valorfinal.get(i), "*");
                    while (st.hasMoreTokens()) {
                        valortemp.add(st.nextToken());
                    }
                    if (i==0) ultimo=valortemp.get(valortemp.size()-1);
                    if (i==1) primeto=valortemp.get(0);
                }else{
                    if (i==0) {
                        ultimo=valorfinal.get(i);
                    }
                    if (i==1) {
                        primeto=valorfinal.get(i);
                    }
                }
            }
            if (ultimo.contains("-")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(ultimo, "-");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                ultimo=valortemp2.get(valortemp2.size()-1);
            }
            if (primeto.contains("-")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(primeto, "-");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                primeto=valortemp2.get(0);
            }
            double resp= Double.parseDouble(ultimo)/Double.parseDouble(primeto);
            val1=val1.replace(ultimo+"/"+primeto, ""+resp);
            if (val1.contains("/")) {
                val1=Division(val1);
            }
        }
        return val1;
    }
    private static String Multiplicacion(String val1){
        String ultimo="";
        String primeto="";
        if (val1.contains("*")) {
            List<String> valorfinal = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(val1, "*");
            while (st.hasMoreTokens()) {
                valorfinal.add(st.nextToken());
            }
            
            ultimo=valorfinal.get(0);
            primeto=valorfinal.get(1);
             
            if (ultimo.contains("-")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(ultimo, "-");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                ultimo=valortemp2.get(valortemp2.size()-1);
            }
            if (primeto.contains("-")) {
                List<String> valortemp2 = new ArrayList<>();
                st = new StringTokenizer(primeto, "-");
                while (st.hasMoreTokens()) {
                    valortemp2.add(st.nextToken());
                }
                primeto=valortemp2.get(0);
            }
            double resp= Double.parseDouble(ultimo)*Double.parseDouble(primeto);
            String remplaso= ultimo+"*"+primeto;
            val1=val1.replace(remplaso, ""+resp);
            if (val1.contains("*")) {
                val1=Multiplicacion(val1);
            }
        }
        return val1;
    }
    private static String Resta(String val1){
        String ultimo="";
        String primeto="";
        if (val1.contains("-")) {
            List<String> valorfinal = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(val1, "-");
            while (st.hasMoreTokens()) {
                valorfinal.add(st.nextToken());
            }
            ultimo=valorfinal.get(0);
            primeto=valorfinal.get(1);
            double resp= Double.parseDouble(ultimo)-Double.parseDouble(primeto);
            String remplaso= ultimo+"-"+primeto;
            val1=val1.replace(remplaso, ""+resp);
            double oo=0;
            try {
                oo=Double.parseDouble(val1);
            } catch (Exception e) {
            }
            if (val1.contains("-")&& oo>=0) {
                val1=Resta(val1);
            }
        }
        return val1;
    }
    public static String voltearArreglo(List<String> inicio){
        String fin = "";
        for (int i = inicio.size()-1; i >= 0; i--) {
            fin+=inicio.get(i);
        }
        return fin;
    }
    
}
