package com.pedroalmir.athena.impl.teamAllocation.util;

import java.io.*;
import java.util.ArrayList;

import com.pedroalmir.athena.impl.teamAllocation.model.Desenvolvedor;

public class Arquivo {
	@SuppressWarnings({ "resource", "unused" })
	public static ArrayList<Desenvolvedor> load(String diretorio) {
		ArrayList<Desenvolvedor> dados = new ArrayList<Desenvolvedor>();
		BufferedReader in = null;
		double conhecimento, habilidade, atitude, salario;
		String nome;
		String[] aux;

		try {
			in = new BufferedReader(new FileReader(diretorio));
			in.readLine();
			for (int i = 0; in.ready(); i++) {
				aux = in.readLine().split(";");
				nome = aux[0];
				conhecimento = Double.parseDouble(aux[1]);
				habilidade = Double.parseDouble(aux[2]);
				atitude = Double.parseDouble(aux[3]);
				salario = Double.parseDouble(aux[4]);
				dados.add(new Desenvolvedor(nome, salario, atitude, conhecimento, habilidade));
			}

			return dados;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "resource", "unused" })
	public static ArrayList<Double> loadObjetivo(String arquivo) {
		ArrayList<Double> dados = new ArrayList<Double>();
		double dado;
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(arquivo));
			for (int i = 0; in.ready(); i++) {
				dado = Double.parseDouble(in.readLine());
				dados.add(dado);
			}

			return dados;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "unused", "resource" })
	public static ArrayList<String> loadVar() {
		ArrayList<String> dados = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("VAR"));
			for (int i = 0; in.ready(); i++) {
				dados.add(in.readLine());
			}

			return dados;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void save(String path, ArrayList<Double> dados) {

		try {
			// BufferedReader in = new BufferedReader(new FileReader("dados/Experimento/Produtividade.csv"));

			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			for (int i = 0; i < dados.size(); i++) {
				bw.write(dados.get(i) + ";");
				bw.newLine();
			}

			bw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveEquipes(ArrayList<String> equipes, ArrayList<Double> saida, ArrayList<Integer> marcado) {
		try {
			FileOutputStream fos = new FileOutputStream("dados/Equipes.csv");
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			for (int i = 0; i < marcado.size(); i++) {
				bw.write(equipes.get(marcado.get(i)) + " ; " + saida.get(marcado.get(i)));
				bw.newLine();
			}

			bw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void merge(String file1, String file2, String destino) {
		try {
			BufferedReader arq1 = new BufferedReader(new FileReader(file1));
			BufferedReader arq2 = new BufferedReader(new FileReader(file2));

			FileOutputStream fos = new FileOutputStream(destino, true);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			while (arq1.ready()) {
				bw.write(arq1.readLine() + arq2.readLine());
				bw.newLine();
			}

			arq1.close();
			arq2.close();
			bw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveFcl(int valor) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("tipperModelo.fcl"));
			PrintWriter saida = new PrintWriter(new OutputStreamWriter(new FileOutputStream("tipperSaida.fcl"), "UTF-8"));
			String str;
			int cont = 0;
			while (in.ready()) {
				str = in.readLine();
				if ((str.contains("TERM")) && (cont < 6)) {
					str.replace("3", 15 + "");
					str.replace("4", 20 + "");
					str.replace("5", 25 + "");
					str.replace("6", 30 + "");
					str.replace("7", 35 + "");
					str.replace("10", 50 + "");
					saida.println(str);
					cont++;
				} else
					saida.println(str);
			}
			in.close();
			saida.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
