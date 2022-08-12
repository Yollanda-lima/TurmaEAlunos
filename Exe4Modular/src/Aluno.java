import java.time.DayOfWeek;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Aluno {
	
	/*
	 * Um aluno sera aprovado se conseguir no mínimo 60 pontos e 75% de frequencia Ok
	 */

	protected final int QTD_MAX_TURMAS = 1;
	protected double notaTotal;
	protected double frequencia;
	protected String nome;
	protected boolean aprovacao;
	protected int turma;

	Scanner entrada = new Scanner(System.in);

	Aluno(String nome, int turma, int matricula) {
		this.nome = nome;
		this.turma = turma;
		this.matricula = matricula;
	}
	
	public Aluno(int i, String string) {
		this.matricula = matricula;
		this.nome = nome;
		this.turma = (Integer) null;
		this.frequencia = -1;
	}

	private int matricula;
	private Vector<Integer> notas = new Vector<Integer>();
	

	void adicionarNotas() {
		double notaTotal = 0;
		for(int i=0; i<4; i++) {
			double nota = entrada.nextDouble();
			if(nota<0 || nota>25) {
				nota=0;
		}
		notaTotal += nota;
	}
		this.notaTotal = notaTotal;
	}


	void validarAprovado() {
		if ((this.notaTotal >= 60) && (this.frequencia >= 70.0)) {
			this.aprovacao = true;
		} else
			this.aprovacao = false;
	}
	
	public void fazerAvaliacao() {
		Random rand = new Random();
		int upperbound = 16;
		int int_random = rand.nextInt(upperbound) + 10;
		notas.add(int_random);
	}
	public double freq() {
		if (frequencia == -1) {
			Random rand = new Random();
			int upperbound = 101;
			for (int i = 0; i < 20; i++) {
				int int_random = rand.nextInt(upperbound);
				if (int_random > 10) {
					frequencia++;
				}
			}
			frequencia /= 20.0;
		}
		return frequencia;
	}
	public String getCodTurma() {
		String cod = "";
		cod += this.nivel;
		cod += this.d.getValue();
		cod += this.getCodPeriodo();
		return cod;
	}

	private int getCodPeriodo() {
		if (this.periodo == Periodo.manha) {
			return 1;
		} else if (this.periodo == Periodo.tarde) {
			return 2;
		} else if (this.periodo == Periodo.noite) {
			return 3;
		}
		return -1;
	}

	public enum Periodo {
		manha, tarde, noite;
	}

	private int nivel;
	private DayOfWeek d;
	private Periodo periodo;
	private Object t;

	public int getDesempenho() {
		return (int) (0.8 * getNotaTotal() + 0.2 * frequencia);
	}


	public double getNotaTotal() {
		return this.verAprov();
	}


	public boolean setT(Turma turma) {
		if (this.t == null) {
			this.t = turma;
			return true;
		} else {
			return false;
		}
	}

	private double verAprov() {
		double media = 0;
		for (Integer nota : notas) {
			media += nota;
		}
		return media;
	}
	
	public String alunoAprov() {
		this.validarAprovado();
		if(this.aprovacao) {
			return "Aluno Aprovado";
		}
		return "Aluno Reprovado";
	}
	
	@Override
	public String toString() {
		return this.nome + "Nota total: " + this.getNotaTotal() + "" + this.alunoAprov();
	}
	
	public String getNome() {
		return this.nome;
	}
}
