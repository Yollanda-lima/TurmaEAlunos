import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Turma {

	/*
	 * Cada turma com 20 alunos Alunos de cada turma realizam 4 atividades porsemestre OK 
	 * Todas as atividades valem 100 pontos
	 * Turmas divididas em nível de ensino
	 * Cada nível gera um código de turma (nível, dia da semana, turno) Ok
	 * Cada turma tem uma aula por semana e o semestre tem 20 semanas Ok
	 * Média das notasda turma Média de frequencia da turma
	 */

	protected final int QTD_PROVAS = 4;
	protected final double NOTA_MAX = 100;
	protected int codTurma;
	protected final int SEMANAS = 20;
	protected final int AULAS_POR_SEMANA = 1;
	protected int diaDaSemana;
	protected int turno;
	private int nivel;
	private DayOfWeek d;
	private Periodo p;
	private ArrayList<Aluno> alunos;
	private static Comparator<Aluno> ordemAlfabetica = new
	Comparator<Aluno>() {
		@Override
		public int compare(Aluno aluno1, Aluno aluno2) {
			return aluno1.getNome().compareTo(aluno2.getNome());
		}
	};

	Turma(int nivel, int diaDaSemana, int turno) { 
		this.alunos = new ArrayList<Aluno>();; 
		this.nivel = nivel;
		this.diaDaSemana = diaDaSemana;
		this.turno = turno;
		
		
		String juntar = "";
		juntar.concat(paraString(nivel));
		juntar.concat(paraString(diaDaSemana));
		juntar.concat(paraString(turno));
		
		this.codTurma = paraInteiro(juntar);
	}

	protected String paraString(int inteiro) {
		String nova = Integer.toString(inteiro);
		return nova;
	}
	
	protected int paraInteiro(String palavra) {
		int novo = Integer.parseInt(palavra);
		return novo;
	}
	
	public Turma(int nivel, DayOfWeek d, Periodo p) {
		this.nivel = nivel;
		this.d = d;
		this.p = p;
	}
	
	public enum Periodo {
		manha, tarde, noite;
	}
	

	public void organizaAluno() {
		alunos.sort(null);
		
	}

	public String relatorioTurma() {
		Collections.sort(this.alunos, ordemAlfabetica);
		String relatorio = "Relatorio da turma: \n";
		for (Aluno a : alunos) {
			relatorio += a.toString() + "\n";
		}
		return relatorio;
	}

	public Aluno getMelhorAlu() {
		Aluno melhor = alunos.get(0);
		for (Aluno a : alunos) {
			if (melhor.getDesempenho() < a.getDesempenho()) {
				melhor = a;
			}
		}
		return melhor;
	}

	public Object getCodTurma() {
		String cod = "";
		cod += this.nivel;
		cod += this.d.getValue();
		cod += this.getCodPeriodo();
		return cod;
	}

	private int getCodPeriodo() {
		if (this.p == Periodo.manha) {
			return 1;
		} else if (this.p == Periodo.tarde) {
			return 2;
		} else if (this.p == Periodo.noite) {
			return 3;
		}
		return -1;
	}

	public boolean cadastrarAluno(Aluno matricula) {
		if (alunos.size() < 20) {
			boolean cadastrado = matricula.setT(this);

			if (cadastrado == true) {
				alunos.add(matricula);
				return true;
			} else {
				System.out.println("Aluno já matriculado");
				return false;
			}

		}
		System.out.println("Turma cheia"
				+ "");
		return false;
		
	}
	

	public void diaProva() {
		for (Aluno matricula : alunos) {
			matricula.fazerAvaliacao();
		}
	}
	
	public double tirarMedNotas() {
		double media = 0;
		for (Aluno matricula : alunos) {
			media += matricula.getNotaTotal();
		}
		media /= alunos.size();
		return media;
	}
	
	public double tirarMedFreq() {
		double media = 0;
		for (Aluno matricula : alunos) {
			media += matricula.freq();
		}
		media /= alunos.size();
		return media;
	}
	
	@Override
	public String toString() {
		return "Turma" + this.codTurma + "| Alunos Matriculados: " + this.alunos.size();
	}
	
	
}
