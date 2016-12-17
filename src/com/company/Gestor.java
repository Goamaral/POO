package com.company;

import java.io.*;
import java.util.*;

import static java.lang.Character.isLetter;

public class Gestor {
    private static ArrayList<Exame> exames = new ArrayList<>();
    private static ArrayList<Pessoa> pessoas = new ArrayList<>();
    private static ArrayList<Curso> cursos = new ArrayList<>();
	private static ArrayList<Sala> salas = new ArrayList<>();
    private static ArrayList<String> historico = new ArrayList<>();
    private static String[] menuPrincipal = {"Listar", "Criar exames", "Configurar exames", "Lancar notas", "Sair"};
    private static String[] menuListar = {"Listar exames", "Listar alunos inscritos num exame", "Listar exames de um aluno", "Listar funcionarios de um exame", "Listar exames de um funcionario", "Listar notas"};
    private static String[] menuConfigurarExame = {"Editar vigilantes", "Editar assistentes", "Editar aluno"};

    public static void main(String[] args) {
        boolean repeat;
        Scanner scanner = new Scanner(System.in);
        String opcao;

        carregarDados();

        while(true) {
            if(menu() == 0) {
                break;
            }
        }

        guardarDados();

        do {
            System.out.println("Quer guardar o historico? (s/n)");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "s":
                    guardarHistorico();
                    repeat = false;
                    break;
                case "n":
                    repeat = false;
                    break;
                default:
                    System.out.println("Opcao invalida");
                    repeat = true;
                    break;
            }
        } while (repeat);

        sair();
    }

    //Listar exames em exames
    private static void listarExames() {
        //Imprimir exames
        for (Exame exame : exames) {
            System.out.println(exame.toString());
        }

        System.out.println("");
    }

    //Criar menu de escolha com a lista de todos os exames e devolver o exame escolhido
    private static Exame escolhaListaExames() {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);

		System.out.println("Escolha um exame:\n");

        //Imprimir exames
        for (Exame exame : exames) {
            System.out.printf("[%d] %s\n", i, exame.toString());
            ++i;
        }

        System.out.print("\nOpcao: ");
        try {
            opcaoInteger = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }

        try {
            return exames.get(opcaoInteger);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }
    }

    //Devolver todos os alunos que estao inscritos em exames, sem repeticao de alunos
    private static ArrayList<Aluno> getAlunosInscritosExames() {
        ArrayList<Aluno> out = new ArrayList<>();

        for(Exame exame : exames) {
            ArrayList<InscritoExame> resultados = exame.getResultados();
            for (InscritoExame inscritoExame : resultados)
                if (!out.contains(inscritoExame.getAluno())) out.add(inscritoExame.getAluno());
        }

        if(out.size() == 0) {
            System.out.println("Nao foram encontrados alunos inscritos");
            return null;
        }

        return out;
    }

    //Criar menu de escolha com a lista de todos os alunos inscritos em exames, sem repeticao, e devolver o aluno escolhido
    private static Aluno escolhaListaAlunosInscritosExame() {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Aluno> alunos = getAlunosInscritosExames();

        if(alunos == null) {
            return null;
        }

        for(Aluno aluno : alunos) {
            System.out.println("[" + i + "] " + aluno.toString());
            ++i;
        }

        System.out.print("\nOpcao: ");
        try {
            opcaoInteger = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }

        try {
            return alunos.get(opcaoInteger);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }
    }

    //Verificar se existem exames no sistema
    private static boolean examesDisponiveis() {
        if(exames.size() == 0) {
            System.out.println("Nao existem exames no sistema");
            return false;
        }
        return true;
    }

    //Devolver todos os funcionarios assoaciados a exames, sem repeticao de funcionarios
    private static ArrayList<Funcionario> getFuncionariosExames() {
        ArrayList<Funcionario> out = new ArrayList<>();

        for(Exame exame : exames) {
            for(Funcionario funcionario : exame.getAssistentes()) {
                if(!out.contains(funcionario)) {
                    out.add(funcionario);
                }
            }

            for(Funcionario funcionario : exame.getVigilantes()) {
                if(!out.contains(funcionario)) {
                    out.add(funcionario);
                }
            }
        }

        if(out.size() == 0) {
            System.out.println("Nao foram encontrados funcionarios");
            return null;
        }

        return out;
    }

    //Criar menu de escolha com a lista de todos os funcionarios associados a exames, sem repeticao, e devolver o funcionario escolhido
    private static Funcionario escolhaListaFuncionarios() {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Funcionario> funcionarios = getFuncionariosExames();

        if(funcionarios == null) {
            return null;
        }

        for(Funcionario funcionario : funcionarios) {
            if(funcionario instanceof FuncionarioDocente) {
                System.out.println("[" + i + "] " + funcionario.toString());
            }

            if(funcionario instanceof FuncionarioNaoDocente) {
                System.out.println("[" + i + "] " + funcionario.toString());
            }
            ++i;
        }

        System.out.print("Opcao: ");
        try {
            opcaoInteger = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }

        try {
            return funcionarios.get(opcaoInteger);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }
    }

    //Devolver todos os docentes em pessoas
    private static ArrayList<FuncionarioDocente> getDocentesPessoas() {
        ArrayList<FuncionarioDocente> out = new ArrayList<>();

        for(Pessoa pessoa : pessoas) {
            if(pessoa instanceof FuncionarioDocente) {
                out.add((FuncionarioDocente) pessoa);
            }
        }

        if(out.size()>0) {
            return out;
        }

        return null;
    }

    private static boolean numMecanograficoDisponivel(int num) {
        for(Pessoa pessoa : pessoas) {
            if(pessoa instanceof Aluno) {
                if( ((Aluno) pessoa).getNumAluno() == num ) {
                    System.out.println("Numero mecanografico ja existente");
                    return false;
                }
            } else if(pessoa instanceof FuncionarioDocente){
                if( ((FuncionarioDocente) pessoa).getNumMecanografico() == num ) {
                    System.out.println("Numero mecanografico ja existente");
                    return false;
                }
            } else if( pessoa instanceof FuncionarioNaoDocente ) {
                if( ((FuncionarioNaoDocente) pessoa).getNumMecanografico() == num ) {
                    System.out.println("Numero mecanografico ja existente");
                    return  false;
                }
            }
        }

        return true;
    }

    //Criar menu para criar um objecto FuncionarioDocente e devolver o mesmo
    private static FuncionarioDocente criarDocente() {
        FuncionarioDocente out;
        Scanner sc = new Scanner(System.in);
        String infoString, nome, email,categoria = "Erro", areaDeInvestigacao = "Erro";
        String[] infoArray;
        int numMecanografico = -1, opcao = -1;
        String[] menuCategoria = {"Assistente", "Auxiliar", "Associado", "Catedratico"};
        String[] menuAreaInvestigacao = {"sistemas de informação", "comunicação e telemática", "engenharia de software"};
        boolean repeat;

        while(true){
            repeat = false;
            System.out.print("Nome: ");
            nome = sc.nextLine();

            //Verificar nome valido
            for (char c : nome.toCharArray()) {
                //Pode conter letras e espaços
                if (!isLetter(c) && c != ' ') {
                    System.out.println("Nome invalido");
                    repeat = true;
                }
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat = false;
            System.out.print("Email: ");
            email = sc.nextLine();

            infoArray = email.split("@");

            //So pode conter um @
            if(infoArray.length != 2) {
                System.out.println("Email invalido");
                repeat = true;
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat = false;
            System.out.print("Numero Mecanografico: ");

            infoString = sc.nextLine();

            try {
                numMecanografico = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Apenas podem ser inseridos numeros");
                repeat = true;
            }

            //Verificar se numero ja existe
            if(!repeat) {
                repeat = !numMecanograficoDisponivel(numMecanografico);
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while (true) {
            repeat = false;
            System.out.println("Escolha uma categoria:");
            printMenu(menuCategoria);

            System.out.print("\nOpcao: ");

            infoString = sc.nextLine();

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                repeat = true;
            }

            if(!repeat) {
                try {
                    categoria = menuCategoria[opcao];
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Opcao invalida");
                    repeat = true;
                }
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat = false;
            System.out.println("Escolha uma area de investigacao:");
            printMenu(menuAreaInvestigacao);

            System.out.print("\nOpcao: ");

            infoString = sc.nextLine();

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                repeat = true;
            }

            if(!repeat) {
                try {
                    areaDeInvestigacao = menuAreaInvestigacao[opcao];
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Opcao invalida");
                    repeat = true;
                }
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        out = new FuncionarioDocente(nome, email, numMecanografico, categoria, areaDeInvestigacao);

        if(!pessoas.contains(out)) {
            pessoas.add(out);
        } else {
            System.out.println("Docente já existe");
        }

        return out;
    }

    //Devolver todos os funcionarios nao docentes em pessoas
    private static ArrayList<FuncionarioNaoDocente> getFuncionarioNaoDocentePessoas() {
        ArrayList<FuncionarioNaoDocente> out = new ArrayList<>();

        for(Pessoa pessoa : pessoas) {
            if(pessoa instanceof FuncionarioNaoDocente) {
                out.add((FuncionarioNaoDocente) pessoa);
            }
        }

        if(out.size()>0) {
            return out;
        }

        return null;
    }

    //Criar menu para criar um objecto FuncionarioNaoDocente e devolver o mesmo
    private static FuncionarioNaoDocente criarAssistente() {
        FuncionarioNaoDocente out;
        Scanner sc = new Scanner(System.in);
        String infoString, nome, email,categoria = "Erro", cargo = "Erro";
        String[] infoArray;
        int numMecanografico = 0, opcao = 0;
        String[] menuCategoria = {"assistente operacional", "assistente técnico", "técnico superior","técnico de informática", "especialista de informática"};
        String[] menuCargo = {"secretaria", "financeiro", "apoio técnico"};
        boolean repeat;

        while(true){
            repeat = false;
            System.out.print("Nome: ");
            nome = sc.nextLine();

            //Verificar nome valido
            for (char c : nome.toCharArray()) {
                //Pode conter letras e espaços
                if (!isLetter(c) && c != ' ') {
                    System.out.println("Nome invalido");
                    repeat = true;
                }
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat = false;
            System.out.print("Email: ");
            email = sc.nextLine();

            infoArray = email.split("@");

            //So pode conter um @
            if(infoArray.length != 2) {
                System.out.println("Email invalido");
                repeat = true;
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat = false;
            System.out.print("Numero Mecanografico: ");

            infoString = sc.nextLine();

            try {
                numMecanografico = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Apenas podem ser inseridos numeros");
                repeat = true;
            }

            if(!repeat) {
                repeat = !numMecanograficoDisponivel(numMecanografico);
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while (true) {
            repeat = false;
            System.out.println("Escolha uma categoria:");
            printMenu(menuCategoria);

            System.out.print("\nOpcao: ");

            infoString = sc.nextLine();

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                repeat = true;
            }

            if(!repeat) {
                try {
                    categoria = menuCategoria[opcao];
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Opcao invalida");
                    repeat = true;
                }
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat = false;
            System.out.println("Escolha uma area de investigacao:\n");
            printMenu(menuCargo);

            System.out.print("\nOpcao: ");

            infoString = sc.nextLine();

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                repeat = true;
            }

            if(!repeat) {
                try {
                    cargo = menuCategoria[opcao];
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Opcao invalida");
                    repeat = true;
                }
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }


        out = new FuncionarioNaoDocente(nome, email, numMecanografico, categoria, cargo);

        if(!pessoas.contains(out)) {
            pessoas.add(out);
        } else {
            System.out.println("Assistente já existe");
        }

        return out;
    }

    //Criar menu de escolha com a lista de todos os objectos do tipo FuncionarioNaoDocente na lista de pessoas e devolver o FuncionarioNaoDocente escolhido
    private static FuncionarioNaoDocente escolhaFuncionarioNaoDocenteListaPessoas() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        int opcao;
        ArrayList<FuncionarioNaoDocente> funcionarioNaoDocentes = getFuncionarioNaoDocentePessoas();

        if(funcionarioNaoDocentes == null) {
            return null;
        }

        for(FuncionarioNaoDocente funcionarioNaoDocente : funcionarioNaoDocentes) {
            System.out.println("[" + i + "] " + funcionarioNaoDocente.toString());

            ++i;
        }

        System.out.println("[" + i + "] Criar assistente");
        System.out.print("\nOpcao: ");

        try {
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida");
            return null;
        }

        try {
            return funcionarioNaoDocentes.get(opcao);
        } catch(IndexOutOfBoundsException e) {
            //ultima opcao
            if(opcao == funcionarioNaoDocentes.size()) {
                return criarAssistente();
            }
            System.out.println("Opcao invalida");
            return null;
        }
    }

    //Devolver todos os alunos em pessoas
    private static ArrayList<Aluno> getAlunosPessoas() {
        ArrayList<Aluno> out = new ArrayList<>();

        for(Pessoa pessoa : pessoas) {
            if(pessoa instanceof Aluno) {
                out.add((Aluno) pessoa);
            }
        }

        if(out.size()>0) {
            return out;
        }

        return null;
    }

    //Devolver todos os cursos que incluem um dada disciplina
    private static ArrayList<Curso> getCursosComDisciplina(Disciplina disciplina) {
        ArrayList<Curso> out = new ArrayList<>();

        for(Curso curso : cursos) {
            if(curso.getDisciplinas().contains(disciplina)) {
                out.add(curso);
            }
        }

        if(cursos.size()==0) {
            return null;
        }

        return out;
    }

    //Criar menu de escolha com a lista de todos os cursos que contem uma dada disciplina e devolver curso escolhido
    private static Curso escolhaListaCursosComDisciplina(Disciplina disciplina) {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Curso> cursos = getCursosComDisciplina(disciplina);

        if(cursos==null) {
            cursos = new ArrayList<>();
        }

        if(cursos.size() == 0) {
            return new Curso();
        }

        for(Curso curso : cursos) {
            System.out.println("[" + i + "] " + curso.toString());
            ++i;
        }

        System.out.print("\nOpcao: ");
        try {
            opcaoInteger = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }

        try {
            return cursos.get(opcaoInteger);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }
    }

    //Criar menu para criar um objecto Aluno e devolver o mesmo
    private static Aluno criarAluno(Disciplina disciplina) {
        Aluno out;
        Scanner sc = new Scanner(System.in);
        String infoString, nome, email, regime = "Erro";
        String[] infoArray;
        int numMecanografico = 0, opcao=0, ano = 0;
        Curso curso;
        String[] menuRegime = {"normal", "trabalhador-estudante", "atleta", "dirigente associativo","aluno de Erasmus"};
        boolean repeat;

        while(true){
            repeat = false;
            System.out.print("Nome: ");
            nome = sc.nextLine();

            //Verificar nome valido
            for (char c : nome.toCharArray()) {
                //Pode conter letras e espaços
                if (!isLetter(c) && c != ' ') {
                    System.out.println("Nome invalido");
                    repeat = true;
                }
            }

            //Passou todos os testes
           if(!repeat) {
               break;
           }
        }

        while(true) {
            repeat = false;
            System.out.print("Email: ");
            email = sc.nextLine();

            infoArray = email.split("@");

            //So pode conter um @
            if(infoArray.length != 2) {
                System.out.println("Email invalido");
                repeat = true;
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat=false;
            System.out.print("Numero Mecanografico: ");

            infoString = sc.nextLine();

            try {
                numMecanografico = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Apenas podem ser inseridos numeros");
                repeat = true;
            }

            if(!repeat) {
                repeat = !numMecanograficoDisponivel(numMecanografico);
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat=false;
            System.out.print("Ano: ");

            infoString = sc.nextLine();

            try{
                ano = Integer.parseInt(infoString);
            } catch(NumberFormatException e) {
                System.out.println("Insira um ano valido");
                repeat=true;
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while (true) {
            repeat = false;
            System.out.println("Curso: \n");

            curso = escolhaListaCursosComDisciplina(disciplina);

            if(curso == null) {
                repeat=true;
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while (true) {
            repeat = false;
            System.out.println("Escolha um tipo de estudante:\n");
            printMenu(menuRegime);

            System.out.print("\nOpcao: ");

            infoString = sc.nextLine();

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida\n");
                repeat=true;
            }

            if(!repeat) {
                try {
                    regime = menuRegime[opcao];
                    if(opcao == 1 || opcao == 2 || opcao == 3 || curso.getDuracao() <= ano) {
                        regime = "Especial";
                    } else {
                        regime = "Normal";
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Opcao invalida\n");
                    repeat = true;
                }
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        out = new Aluno(nome, email, numMecanografico, curso, ano, regime);

        if(out.getNome().equals("Nao existente")) {
            return out;
        }

        if(!pessoas.contains(out)) {
            pessoas.add(out);
            disciplina.inscreverAluno(out);
        } else {
            System.out.println("Aluno já existe");
            disciplina.inscreverAluno(out);
        }

        return out;
    }

    //Criar menu de escoha com lista de todos os alunos em pessoas e devolver aluno escolhido
    private static Aluno escolhaAlunoListaPessoas(Disciplina disciplina) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        int opcao;
        ArrayList<Aluno> alunos = getAlunosPessoas();

        if(alunos == null) {
            return null;
        }

        for(Aluno aluno : alunos) {
            System.out.println("[" + i + "] " + aluno.toString());

            ++i;
        }

        System.out.println("[" + i + "] Criar aluno");
        System.out.print("\nOpcao: ");

        try {
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida\n");
            return null;
        }

        try {
            return alunos.get(opcao);
        } catch(IndexOutOfBoundsException e) {
            //ultima opcao
            if(opcao == alunos.size()) {
                return criarAluno(disciplina);
            }
            System.out.println("Opcao invalida\n");
            return null;
        }
    }

	//Criar menu de escolha de uma disciplina e devolver disciplina escolhida
	private static Disciplina escolhaListaDisciplinas(ArrayList<Disciplina> disciplinas){
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);

		System.out.println("Escolha uma disciplina:\n");

        for (Disciplina disciplina : disciplinas){
            System.out.println("[" + i + "] " + disciplina.toString());
            i++;
        }

        System.out.print("\nOpcao: ");

        try {
            opcaoInteger = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida");
            return null;
        }

        try{
            return disciplinas.get(opcaoInteger);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Opcao invalida");
            return null;
        }
    }

	//Criar menu de escolha de uma sala ou criar uma nova sala e devolver a sala escolhida
	private static Sala escolhaListaSalas(){
		int i = 0;
		int opcaoInteger;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Escolha uma sala:\n");

		for (Sala sala : salas){
			System.out.println("[" + i + "] " + sala.toString());
			++i;
		}

		System.out.println("[" + i + "] Criar sala");

		System.out.print("\nOpcao: ");

		try {
			opcaoInteger = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Insira uma opcao valida");
			return null;
		}

		try{
			return salas.get(opcaoInteger);
		}catch(IndexOutOfBoundsException e){
			if (opcaoInteger == salas.size()){
				return criarSala();
			}
			System.out.println("Insira uma opcao valida");
			return null;
		}
	}

	//Criar menu para a criacao de uma sala
	private static Sala criarSala(){
        String id;
        Sala out;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome da sala: ");
        id = scanner.nextLine();

        out = new Sala(id);

        if (salas.contains(out)){
            System.out.println("Sala ja existente");
            return out;
        }

        salas.add(out);

        return out;
    }

	//Criar menu para criar objecto intervalo de tempo para a criacao de um exame
	private static Calendar criarInicioExame(){
        Calendar out = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        String stringRecebida;
        String []parts;
        Scanner scanner = new Scanner(System.in);
        int ano, mes, dia, hora, minuto;
        boolean anoBisexto = false;

        int []meses31 = {1, 3, 5, 7, 8, 10, 12};
        int []meses30 = {4, 6, 9, 11};

        System.out.println("Formato da data <dia> <mes> <ano> <hora>:<minutos>");
		System.out.print("Data: ");
        stringRecebida = scanner.nextLine();

        parts = stringRecebida.split(" ");

        if(parts.length != 4) {
            System.out.println("Formato de data invalido");
            return null;
        }

        //Verificar ano
        try {
            ano = Integer.parseInt(parts[2]);
        } catch(NumberFormatException e) {
            System.out.println("Ano inserido invalido");
            return null;
        }

        //Verificar se é bisexto
        if(ano%4==0) {
            if( !(ano%100==0 && ano%400!=0) ) {
                anoBisexto = true;
            }
        }

        //Verificar mes
        try {
            mes = Integer.parseInt(parts[1]);
        } catch(NumberFormatException e) {
            System.out.println("Mes inserido invalido");
            return null;
        }

        //Verificar se é menor ou igual a 12
        if(mes > 12) {
            System.out.println("Mes inserido invalido");
            return null;
        }

		//No objecto Calendar 0 é Janeiro
		mes = mes - 1;

        //Verificar dia
        try {
            dia = Integer.parseInt(parts[0]);
        } catch(NumberFormatException e) {
            System.out.println("Dia inserido invalido");
            return null;
        }

        //Verificar se dia existe em mes
        //meses com 31 dias
        if(Arrays.asList(meses31).contains(mes)) {
            //Verificar se dia maior que 31
            if(dia > 31) {
                System.out.println("Dia inserido invalido");
                return null;
            }
        }
        //meses com 30 dias
        else if(Arrays.asList(meses30).contains(mes)) {
            //Verificar se dia maior que 30
            if(dia > 30) {
                System.out.println("Dia inserido invalido");
                return null;
            }
        }
        //Fevereiro
        else {
            //Verificar se é ano bisexto
            if(anoBisexto) {
                //Verificar se dia é maior que 29
                if(dia>29) {
                    System.out.println("Dia inserido invalido");
                    return null;
                }
            } else {
                //Verificar se dia é maior que 28
                if(dia>28) {
                    System.out.println("Dia inserido invalido");
                    return null;
                }
            }
        }

        parts = parts[3].split(":");

        //Verificar hora
        try {
            hora = Integer.parseInt(parts[0]);
        } catch(NumberFormatException e) {
            System.out.println("Hora inserido invalido");
            return null;
        }

        //Verificar se hora é maior que 23
        if(hora>23) {
            System.out.println("Hora inserido invalido");
            return null;
        }

        //Verificar minutos
        try {
            minuto = Integer.parseInt(parts[1]);
        } catch(NumberFormatException e) {
            System.out.println("Minuto inserido invalido");
            return null;
        }

        //Verificar se hora é maior que 59
        if(minuto>59) {
            System.out.println("Minuto inserido invalido");
            return null;
        }

        out.set(ano, mes, dia, hora, minuto);

        //Verificar se out é no futuro
        if(out.compareTo(now) <= 0) {
            System.out.println("Data inserida invalida");
            return null;
        }

        return out;
    }

	//Criar menu para a escolha de um docente da lista de Pessoas ou criar um, e devolver o docente
	private static FuncionarioDocente escolhaListaDocentesPessoa(){
		int opcaoInteger;
		Scanner scanner = new Scanner(System.in);
		int i = 0;

		//carregar todos os funcionarios para a lista funcionarios
		ArrayList<FuncionarioDocente> docentes = getDocentesPessoas();

		System.out.println("Escolha um docente:\n");

        if(docentes == null) {
            docentes = new ArrayList<>();
        }

        //imprimir a lista de funcionarios docentes obtida
        for (FuncionarioDocente funcionarioDocente : docentes){
            System.out.println("[" + i + "] " + funcionarioDocente.toString());
            i++;
        }

        System.out.printf("[%d] Criar docente\n", i);

		System.out.print("\nOpcao: ");
		try {
			opcaoInteger = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Insira uma opcao valida");
			return null;
		}

		try {
			return docentes.get(opcaoInteger);
		} catch (IndexOutOfBoundsException e) {
            if(opcaoInteger == docentes.size()) {
                return criarDocente();
            }
			System.out.println("Insira uma opcao valida");
			return null;
		}
	}

	private static String escolherEpoca() {
        String[] epoca = {"Normal", "Recurso", "Especial"};
        int i=0;
        int opcao;
        Scanner scanner = new Scanner(System.in);

		System.out.println("Escolha uma epoca:\n");
        for(String string : epoca) {
            System.out.println("[" + i + "] " + string);
            ++i;
        }

        System.out.print("\nOpcao: ");

        try {
            opcao = Integer.parseInt(scanner.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Opcao invalida");
            return null;
        }

        try {
            return epoca[opcao];
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Opcao invalida");
            return null;
        }
    }

	private static int perguntarDuracao() {
        Scanner scanner = new Scanner(System.in);

		System.out.printf("Introduza a duracao do exame (em minutos): ");
		try{
			return Integer.parseInt(scanner.nextLine());
		}catch(NumberFormatException e){
			System.out.println("Numero de minutos invalido");
			return 0;
		}
	}

    //Guardas dados em ficheiro
    private static void guardarDados() {
		//Guardar cursos
        try {
            FileOutputStream databaseCursos = new FileOutputStream("cursos.db");
            ObjectOutputStream objectCursos = new ObjectOutputStream(databaseCursos);
            objectCursos.writeObject(cursos);
            objectCursos.close();
            databaseCursos.close();
        } catch (IOException e) {
            System.out.println("Erro na escrita do ficheiro cursos.db");
            return;
        }

		//Guardar pessoas
        try {
            FileOutputStream databasePessoas = new FileOutputStream("pessoas.db");
            ObjectOutputStream objectPessoas = new ObjectOutputStream(databasePessoas);
            objectPessoas.writeObject(pessoas);
            objectPessoas.close();
            databasePessoas.close();
        } catch (IOException e) {
            System.out.println("Erro na escrita do ficheiro pessoas.db");
            return;
        }

		//Guardar exames
        try {
            FileOutputStream databaseExames = new FileOutputStream("exames.db");
            ObjectOutputStream objectExames = new ObjectOutputStream(databaseExames);
            objectExames.writeObject(exames);
            objectExames.close();
            databaseExames.close();
        } catch (IOException e) {
            System.out.println("Erro na escrita do ficheiro exames.db");
            return;
        }

		//Guardar salas
        try {
            FileOutputStream databaseSalas = new FileOutputStream("salas.db");
            ObjectOutputStream objectSalas = new ObjectOutputStream(databaseSalas);
            objectSalas.writeObject(salas);
            objectSalas.close();
            databaseSalas.close();
        } catch (IOException e) {
            System.out.println("Erro na escrita do ficheiro salas.db");
            return;
        }

		System.out.println("Dados gravados com sucessso");
    }

    private static void guardarHistorico() {
        //Guardar historico
        try {
            PrintWriter writer = new PrintWriter("historico.txt");
            for(String string : historico) {
                writer.println(string);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro na escrita do historico cursos.db");
        }
    }

    private static void carregarDados() {
		//Carregar cursos
        try {
            FileInputStream databaseCursos = new FileInputStream("cursos.db");
            ObjectInputStream objectCursos = new ObjectInputStream(databaseCursos);
            cursos = (ArrayList<Curso>) objectCursos.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro cursos.db nao encontrado");
        } catch (IOException e) {
            System.out.println("Erro na leitura do ficheiro cursos.db");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Curso nao encontrada");
            return;
        }

        //Carregar pessoas
        try {
            FileInputStream databasePessoas = new FileInputStream("pessoas.db");
            ObjectInputStream objectPessoas = new ObjectInputStream(databasePessoas);
            pessoas = (ArrayList<Pessoa>) objectPessoas.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro pessoas.db nao encontrado");
        } catch (IOException e) {
            System.out.println("Erro na leitura do ficheiro pessoas.db");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Pesssoa nao encontrada");
            return;
        }

        //Carregar exames
        try {
            FileInputStream databaseExames = new FileInputStream("exames.db");
            ObjectInputStream objectExames = new ObjectInputStream(databaseExames);
            exames = (ArrayList<Exame>) objectExames.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro exames.db nao encontrado");
        } catch (IOException e) {
            System.out.println("Erro na leitura do ficheiro exames.db");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Exame nao encontrada");
            return;
        }

		//Carregar salas
        try {
            FileInputStream databaseSalas = new FileInputStream("salas.db");
            ObjectInputStream objectSalas = new ObjectInputStream(databaseSalas);
            salas = (ArrayList<Sala>) objectSalas.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro salas.db nao encontrado");
        } catch (IOException e) {
            System.out.println("Erro na leitura do ficheiro salas.db");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Sala nao encontrada");
        }
    }

    public static Disciplina criarDisciplina(){
        int i;
        Disciplina out;
        boolean repeat;
        int opcao = -1;
        String nome;
        FuncionarioDocente responsavel;
        ArrayList<FuncionarioDocente> outrosDocentes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String[] menuDocentes = {"Adicionar docente", "Sair"};
        FuncionarioDocente outroDocente;

        //Pedir o nome da disciplina
        System.out.print("Nome da disciplina: ");
        nome = scanner.nextLine();

        do {
            repeat = false;
            //Escolher funcionario docente
            System.out.println("--- Escolha um docente para ser o responsável da disciplina ---\n");
            responsavel = escolhaListaDocentesPessoa();

            if(responsavel == null) {
                repeat=true;
            }
        }while(repeat);

        System.out.println("--- Escolha outros docentes para a disciplina ---\n");
        do{
            repeat = false;

            System.out.println("Docentes: ");
            //imprimir docentes ja existentes na disciplina
            for(FuncionarioDocente docente : outrosDocentes){
                System.out.println(docente.toString());
            }

            //escolher o que fazer
            i=0;
            System.out.printf("[%d] %s\n", i, menuDocentes[i]);
            ++i;
            System.out.printf("[%d] %s\n", i, menuDocentes[i]);

            System.out.print("\nOpcao: ");

            try{
                opcao = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Opcao Invalida");
                repeat = true;
            }

            if(!repeat) {
                switch(opcao) {
                    //caso tenha escolhido adicionar outo docente à disciplina
                    case 0:
                        outroDocente = escolhaListaDocentesPessoa();
                        if (outroDocente == null) {
                            repeat = true;
                        }
                        if (!repeat) {
                            if (outrosDocentes.contains(outroDocente) || outroDocente.equals(responsavel)) {
                                System.out.println("Docente ja na lista de docentes");
                                repeat = true;
                            } else {
                                //adicionar o novo docente à lista de outos docentes
                                outrosDocentes.add(outroDocente);
                                repeat=true;
                            }
                        }
                        break;
                    case 1:
                        if (outrosDocentes.size() == 0) {
                            System.out.println("É necessário adicionar um docente à disciplina");
                            repeat = true;
                        }
                        break;
                    default:
                        repeat=true;
                }
            }

        }while(repeat);

        out = new Disciplina(nome, responsavel, outrosDocentes);

        return out;
    }

    private static Curso criarCurso(){
        Curso out;
        int opcao = -1;
        boolean repeat;
        int duracao = -1;
        String nome;
        String grauConfere = null;
        ArrayList<Disciplina> disciplinasCurso = new ArrayList<>();
        String[] graus = {"Licenciatura", "Mestrado", "Doutoramento"};
        int i;

        Scanner scanner = new Scanner(System.in);

        do {
            repeat = false;
            //Pedir o nome do curso
            System.out.print("Nome do curso: ");
            nome = scanner.nextLine();

            //Pedir a duracao, em anos, do curso
            System.out.print("Duracao do curso(anos): ");
            try{
                duracao = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Opcao Invalida");
                repeat=true;
            }
        }while(repeat);

        do {
            i=0;
            repeat = false;
            //Pedir o grau a que o curso confere;
            System.out.println("Grau do curso: \n");

            for(String grau : graus) {
                System.out.printf("[%d] %s\n", i, grau);
                ++i;
            }

            System.out.print("\nOpcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Opcao invalida");
                repeat = true;
            }

            if(!repeat) {
                try {
                    grauConfere = graus[opcao];
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("Opcao invalida");
                    repeat = true;
                }
            }
        }while(repeat);

        do{
            repeat = false;

            //Adicionar disciplinas ao curso
            System.out.println("\n--- Adicionar disciplinas ao curso ---\n");

            System.out.println("Disciplinas:");
            //imprimir as disciplinas já existentes no curso
            for (Disciplina umaDisciplinaCurso : disciplinasCurso){
                System.out.println(umaDisciplinaCurso.toString());
            }

            System.out.println("");

            //escolher o que fazer
            System.out.println("[0] Adicionar uma disciplina");
            System.out.println("[1] Sair");

            System.out.print("\nOpcao: ");

            try{
                opcao = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Opcao Invalida");
                repeat = true;
            }

            if(!repeat) {
                //se escolheu adicionar uma disciplina
                if (opcao == 0){
                    //adicionar a nova disciplina à lista das disciplinas do curso
                    disciplinasCurso.add(criarDisciplina());
                    repeat = true;
                } else {
                    if(disciplinasCurso.size() == 0) {
                        System.out.println("O curso tem que ter pelo menos uma disciplina");
                        repeat = true;
                    }
                }
            }

        }while(repeat);

        out = new Curso(nome, duracao, grauConfere, disciplinasCurso);

        //adicionar o novo curso à lista global de cursos
        cursos.add(out);
        return out;
    }

    private static void sair() {
        System.exit(0);
    }

    private static int menu() {
        Scanner sc = new Scanner(System.in);
        Aluno auxAluno;
        Exame auxExame;
        Funcionario auxFuncionario;
        int auxInt;
        boolean repeat;
        Disciplina auxDisciplina;
        Calendar auxInicio;
        int auxDuracao;
        Sala auxSala;
        boolean repeat2;
        IntervaloTempo auxIntervaloTempo;
        FuncionarioDocente auxDocenteResponsavel;
        String auxEpoca;
        Curso auxCurso;

        System.out.println("--- Gestor de Exames ---" + "\n");
        printMenu(menuPrincipal);
        System.out.print("\nOpcao: ");

        try {
            auxInt = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida\n");
            return 1;
        }

        switch (auxInt) {
            //Listar
            case 0:
                System.out.println("\n--- Listar ---\n");
                printMenu(menuListar);
                System.out.println("[6] Menu principal");
                System.out.print("\nOpcao: ");

                try {
                    auxInt = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.printf("Opcao invalida\n");
                    return 1;
                }

                switch (auxInt) {
                    //Listar exames
                    case 0:
                        historico.add("Listar Exames");
                        System.out.println("\n--- Listar Exames ---\n");
                        if(examesDisponiveis()){
                            listarExames();
                        }

                        System.out.println("Para continuar prima qualquer tecla");
                        sc.nextLine();

                        break;

                    //Listar alunos inscritos num exame
                    case 1:
                        System.out.println("\n--- Listar alunos inscritos num exame ---\n");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();

                            if(auxExame != null) {
                                historico.add("Listar alunos inscritos no exame " + auxExame.toString());
                                auxExame.listarAlunosInscritos();
                            }
                        }

                        System.out.println("Para continuar prima qualquer tecla");
                        sc.nextLine();

                        break;

                    //Listar exames de um aluno
                    case 2:
                        System.out.println("\n--- Listar exames de um aluno ---\n");

                        if(examesDisponiveis()) {
                            auxAluno = escolhaListaAlunosInscritosExame();

                            if(auxAluno != null) {
                                historico.add("Listar exames do aluno " + auxAluno.toString());
                                System.out.println("Exames\n:");
                                auxAluno.listarExames(exames);
                            }
                        }

                        System.out.println("Para continuar prima qualquer tecla");
                        sc.nextLine();

                        break;

                    //Listar funcionarios de um exame
                    case 3:
                        System.out.println("---Listar funcionarios de um exame---\n");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();

                            if(auxExame != null) {
                                historico.add("Listar funcionarios do exame " + auxExame.toString());
                                auxExame.listarFuncionarios();
                            }
                        }

                        System.out.println("Para continuar prima qualquer tecla");
                        sc.nextLine();

                        break;

                    case 4:
                        System.out.println("---Listar exames de um funcionario---\n");

                        if(examesDisponiveis()) {
                            auxFuncionario = escolhaListaFuncionarios();

                            if(auxFuncionario != null) {
                                historico.add("Listar exames do funcionario " + auxFuncionario.toString());
                                auxFuncionario.listarExames(exames);
                            }
                        }

                        System.out.println("\nPara continuar prima qualquer tecla");
                        sc.nextLine();

                        break;

                    case 5:
                        System.out.println("---Listar notas---\n");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();
                            if(auxExame != null) {
                                historico.add("Listar notas do exame " + auxExame.toString());
                                auxExame.listarNotas();
                            }
                        }

                        System.out.println("Para continuar prima qualquer tecla");
                        sc.nextLine();

                        break;

                    case 6:
                        break;

                    default:
                        System.out.println("Opcao invalida\n");
                        break;
                }
                break;
            //Criar exames
            case 1:
                System.out.println("--- Criar Exames ---\n");

                auxInt=0;
                for(Curso curso : cursos) {
                    System.out.printf("[%d] %s\n", auxInt, curso.toString());
                    ++auxInt;
                }

                System.out.printf("[%d] Criar curso\n", auxInt);

                System.out.print("\nOpcao: ");

                try {
                    auxInt = Integer.parseInt(sc.nextLine());
                } catch(NumberFormatException e) {
                    System.out.println("Opcao invalida");
                    break;
                }

                try {
                    auxCurso = cursos.get(auxInt);
                } catch(IndexOutOfBoundsException e) {
                    if(cursos.size() == auxInt) {
                        auxCurso = criarCurso();
                        if(auxCurso == null) {
                            System.out.println("Opcao invalida");
                            break;
                        }
                    }else{
                        break;
                    }
                }

                do {
                    repeat = false;

                    auxDisciplina = escolhaListaDisciplinas(auxCurso.getDisciplinas());

                    if(auxDisciplina == null) {
                        repeat=true;
                    }
                } while(repeat);

                do {
                    repeat = false;
                    auxInicio = criarInicioExame();

                    if(auxInicio == null) {
                        repeat = true;
                    }
                } while(repeat);

                do{
                    repeat = false;
                    auxDuracao = perguntarDuracao();

                    if(auxDuracao == 0) {
                        repeat = true;
                    }
                }while(repeat);

                do {
                    repeat2=false;

                    do {
                        repeat = false;
                        auxSala = escolhaListaSalas();

                        if(auxSala == null) {
                            repeat = true;;
                        }
                    } while(repeat);

                    if (!auxSala.inserirIntervalo(auxInicio, auxDuracao)){
                        System.out.println("Sala ja ocupada na hora selecionada. Escolha outra sala");
                        repeat2=true;
                    }

                } while(repeat2);

                auxIntervaloTempo = new IntervaloTempo(auxInicio, auxDuracao);

                do {
                    repeat = false;
                    auxDocenteResponsavel = escolhaListaDocentesPessoa();
                    if(!auxDisciplina.getResponsavel().equals(auxDocenteResponsavel) && !auxDisciplina.getOutrosDocentes().contains(auxDocenteResponsavel)) {
                        System.out.println("Docente nao pertence à disciplina");
                        repeat = true;
                    }
                } while(repeat);

                do {
                    repeat=false;
                    auxEpoca = escolherEpoca();

                    if(auxEpoca == null) {
                        repeat=true;
                    }
                } while(repeat);

                if(auxEpoca.equals("Especial")) {
                    auxExame = new ExameEspecial(auxDisciplina, auxSala, auxDocenteResponsavel, auxIntervaloTempo);
                } else {
                    auxExame = new ExameNormalRecurso(auxDisciplina, auxSala, auxDocenteResponsavel, auxIntervaloTempo, auxEpoca);
                }

                if(exames.contains(auxExame)) {
                    System.out.println("O exame já existe no sistema");
                } else {
                    exames.add(auxExame);
                }
                break;
            //Configurar exames
			case 2:
				System.out.println("--- Configurar exame ---\n");
				if(examesDisponiveis()) {
					auxExame = escolhaListaExames();
					if(auxExame != null) {
						printMenu(menuConfigurarExame);
                        System.out.println("[3] Menu principal");
                        System.out.print("\nOpcao: ");

                        try {
                            auxInt = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.printf("Opcao invalida\n");
                            break;
                        }

                        switch (auxInt) {
                            //Associar vigilante
                            case 0:
                                System.out.println("--- Associar vigilante --\n");
                                //Escolher funcionario ou criar um
                                auxFuncionario = escolhaListaDocentesPessoa();
                                if(auxFuncionario != null) {
                                    auxExame.inserirDocente((FuncionarioDocente)auxFuncionario);
                                }

                                System.out.println("Para continuar prima qualquer tecla");
                                sc.nextLine();

                                break;
                            //Associar assistente
                            case 1:
                                System.out.println("--- Associar assistente ---\n");
                                auxFuncionario = escolhaFuncionarioNaoDocenteListaPessoas();
                                if(auxFuncionario != null) {
                                    auxExame.inserirAssistente((FuncionarioNaoDocente)auxFuncionario);
                                }

                                System.out.println("Para continuar prima qualquer tecla");
                                sc.nextLine();

                                break;
                            //Associar aluno
                            case 2:
                                System.out.println("--- Associar aluno ---\n");
                                auxAluno = escolhaAlunoListaPessoas(auxExame.getDisciplina());
                                if(auxAluno != null) {
                                    if(auxAluno.getCurso().getNome().equals("Nao existente")) {
                                        System.out.println("Nao existem cursos com a disciplina");
                                    }
                                    auxExame.inscreverAluno(auxAluno);
                                }

                                System.out.println("Para continuar prima qualquer tecla");
                                sc.nextLine();

                                break;
                            default:
                                System.out.printf("Opcao invalida\n");
                                break;
                        }
                    }
                }
                break;
            //Lançar notas
            case 3:
                if(examesDisponiveis()) {
                    auxExame = escolhaListaExames();
                    if(auxExame != null) {
                        auxExame.lancarNotas();
                    }
                }

                System.out.println("Para continuar prima qualquer tecla");
                sc.nextLine();

                break;
            //Sair
            case 4:
                return 0;
            //Opcao invalida
            default:
                System.out.println("Opcao invalida\n");
                break;
        }
        return 1;
    }

    private static void printMenu(String[] menu) {
        for(int i=0; i < menu.length; ++i) {
            System.out.println("[" + i + "] " + menu[i]);
        }
    }
}
