package com.company;

import java.io.*;
import java.util.*;

import static java.lang.Character.isLetter;

public class Gestor {
    private static ArrayList<Exame> exames = new ArrayList<>();
    private static ArrayList<Pessoa> pessoas = new ArrayList<>();
    private static ArrayList<Curso> cursos = new ArrayList<>();
    private ArrayList<Sala> salas = new ArrayList<>();
    private ArrayList<String> historico = new ArrayList<>();
    private String importFileName;
    private String exportFileName;
    private static String[] menuPrincipal = {"Listar", "Criar exames", "Configurar exames", "Lancar notas" , "Sair"};
    private static String[] menuListar = {"Listar exames", "Listar alunos inscritos num exame", "Listar exames de um aluno", "Listar funcionarios de um exame", "Listar exames de um funcionario", "Listar notas"};
    private static String[] menuConfigurarExame = {"Editar vigilantes", "Editar assistentes", "Editar aluno"};

    public static void main(String[] args) {

        /*try {
            carregarDados();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        while(true) {
            if(menu() == 0) {
                break;
            }
        }

        try {
            guardarDados();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sair();
    }

    //Listar exames em exames
    private static void listarExames() {
        //Imprimir exames
        for (Exame exame : exames) {
            System.out.printf("%s, %s, %s, %s, %s, %s, %s",
                    exame.getEpoca(), exame.getDisciplina().getNome(), exame.getData().getInicio(), exame.getData().getDuracao(),
                    exame.getSala().getId(), exame.getVigilantes().size(), exame.getResultados().size());
        }
    }

    //Criar menu de escolha com a lista de todos os exames e devolver o exame escolhido
    private static Exame escolhaListaExames() {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);

        //Imprimir exames
        for (Exame exame : exames) {
            System.out.printf("[%d] %s, %s, %s, %s, %s, %s, %s",
                    i, exame.getEpoca(), exame.getDisciplina().getNome(), exame.getData().getInicio(), exame.getData().getDuracao(),
                    exame.getSala().getId(), exame.getVigilantes().size(), exame.getResultados().size());
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
            System.out.println("[" + i + "]" + aluno.getNumAluno() +
                    " " + aluno.getNome() +
                    " ano: " + aluno.getAno() +
                    " curso: " + aluno.getCurso().getNome() +
                    " regime: " + aluno.getRegime());
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
                System.out.println("[" + i + "] " + funcionario.toStringDetailed());
            }

            if(funcionario instanceof FuncionarioNaoDocente) {
                System.out.println("[" + i + "] " + funcionario.toStringDetailed());
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

    //Criar menu para criar um objecto FuncionarioDocente e devolver o mesmo
    private static FuncionarioDocente criarDocente() {
        FuncionarioDocente out;
        Scanner sc = new Scanner(System.in);
        String infoString, nome, email,categoria, areaDeInvestigacao;
        String[] infoArray;
        int numMecanografico, opcao;
        String[] menuCategoria = {"Assistente", "Auxiliar", "Associado", "Catedratico"};
        String[] menuAreaInvestigacao = {"sistemas de informação", "comunicação e telemática", "engenharia de software"};

        while(true){
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Nome: ");
            nome = sc.nextLine();

            if(nome.equals("")) {
                return null;
            }

            //Verificar nome valido
            for (char c : nome.toCharArray()) {
                //Pode conter letras e espaços
                if (!isLetter(c) && c != ' ') {
                    System.out.println("Nome invalido");
                    continue;
                }
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Email: ");
            email = sc.nextLine();

            if(email.equals("")) {
                return null;
            }

            infoArray = email.split("@");

            //So pode conter um @
            if(infoArray.length != 2) {
                System.out.println("Email invalido");
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Numero Mecanografico: ");

            infoString = sc.nextLine();

            if(email.equals("")) {
                return null;
            }

            try {
                numMecanografico = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Apenas podem ser inseridos numeros");
                continue;
            }

            //Passou todos os testes
            break;
        }

        while (true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.println("Escolha uma categoria:");
            printMenu(menuCategoria);

            infoString = sc.nextLine();

            if(infoString.equals("")) {
                return null;
            }

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            try {
                categoria = menuCategoria[opcao];
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.println("Escolha uma area de investigacao:");
            printMenu(menuAreaInvestigacao);

            infoString = sc.nextLine();

            if(infoString.equals("")) {
                return null;
            }

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            try {
                areaDeInvestigacao = menuCategoria[opcao];
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            //Passou todos os testes
            break;
        }


        out = new FuncionarioDocente(nome, email, numMecanografico, categoria, areaDeInvestigacao);

        if(!pessoas.contains(out)) {
            pessoas.add(out);
        } else {
            System.out.println("Docente já existe");
        }

        return out;
    }

    //Criar menu de escolha com a lista de todos os objectos do tipo FuncionarioDocente na lista de pessoas e devolver o FuncionarioDocente escolhido
    private static FuncionarioDocente escolhaFuncionarioDocenteListaPessoas() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        int opcao;
        ArrayList<FuncionarioDocente> funcionarioDocentes = getDocentesPessoas();

        if(funcionarioDocentes == null) {
            return null;
        }

        for(FuncionarioDocente funcionarioDocente : funcionarioDocentes) {
                System.out.println("[" + i + "] " + funcionarioDocente.getNumMecanografico() + " "
                        + funcionarioDocente.getNome() + " "
                        + funcionarioDocente.getCategoria() + " "
                        + funcionarioDocente.getAreaDeInvestigacao() + " "
                        + funcionarioDocente.getEmail());

                ++i;
        }

        System.out.println("[" + i + "] Criar docente");
        System.out.print("Opcao: ");

        try {
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida");
            return null;
        }

        try {
            return funcionarioDocentes.get(opcao);
        } catch(ArrayIndexOutOfBoundsException e) {
            //ultima opcao
            if(opcao == funcionarioDocentes.size()) {
                return criarDocente();
            }
            System.out.println("Opcao invalida");
            return null;
        }
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
        String infoString, nome, email,categoria, cargo;
        String[] infoArray;
        int numMecanografico, opcao;
        String[] menuCategoria = {"assistente operacional", "assistente técnico", "técnico superior","técnico de informática", "especialista de informática"};
        String[] menuCargo = {"secretaria", "financeiro", "apoio técnico"};

        while(true){
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Nome: ");
            nome = sc.nextLine();

            if(nome.equals("")) {
                return null;
            }

            //Verificar nome valido
            for (char c : nome.toCharArray()) {
                //Pode conter letras e espaços
                if (!isLetter(c) && c != ' ') {
                    System.out.println("Nome invalido");
                    continue;
                }
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Email: ");
            email = sc.nextLine();

            if(email.equals("")) {
                return null;
            }

            infoArray = email.split("@");

            //So pode conter um @
            if(infoArray.length != 2) {
                System.out.println("Email invalido");
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Numero Mecanografico: ");

            infoString = sc.nextLine();

            if(email.equals("")) {
                return null;
            }

            try {
                numMecanografico = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Apenas podem ser inseridos numeros");
                continue;
            }

            //Passou todos os testes
            break;
        }

        while (true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.println("Escolha uma categoria:");
            printMenu(menuCategoria);

            infoString = sc.nextLine();

            if(infoString.equals("")) {
                return null;
            }

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            try {
                categoria = menuCategoria[opcao];
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.println("Escolha uma area de investigacao:");
            printMenu(menuCargo);

            infoString = sc.nextLine();

            if(infoString.equals("")) {
                return null;
            }

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            try {
                cargo = menuCategoria[opcao];
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            //Passou todos os testes
            break;
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
            System.out.println("[" + i + "] " + funcionarioNaoDocente.getNumMecanografico() + " "
                    + funcionarioNaoDocente.getNome() + " "
                    + funcionarioNaoDocente.getCategoria() + " "
                    + funcionarioNaoDocente.getCargo() + " "
                    + funcionarioNaoDocente.getEmail());

            ++i;
        }

        System.out.println("[" + i + "] Criar assistente");
        System.out.print("Opcao: ");

        try {
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida");
            return null;
        }

        try {
            return funcionarioNaoDocentes.get(opcao);
        } catch(ArrayIndexOutOfBoundsException e) {
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

        if(cursos == null) {
            return new Curso();
        }

        for(Curso curso : cursos) {
            System.out.println("[" + i + "] " + curso.getNome() +
                    " Duracao: " + curso.getDuracao() +
                    " Grau: " + curso.getGrauConfere());
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
        String infoString, nome, email, regime;
        String[] infoArray;
        int numMecanografico, opcao, ano;
        Curso curso;
        String[] menuRegime = {"normal", "trabalhador-estudante", "atleta", "dirigente associativo","aluno de Erasmus"};

        while(true){
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Nome: ");
            nome = sc.nextLine();

            if(nome.equals("")) {
                return null;
            }

            //Verificar nome valido
            for (char c : nome.toCharArray()) {
                //Pode conter letras e espaços
                if (!isLetter(c) && c != ' ') {
                    System.out.println("Nome invalido");
                    continue;
                }
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Email: ");
            email = sc.nextLine();

            if(email.equals("")) {
                return null;
            }

            infoArray = email.split("@");

            //So pode conter um @
            if(infoArray.length != 2) {
                System.out.println("Email invalido");
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Numero Mecanografico: ");

            infoString = sc.nextLine();

            if(email.equals("")) {
                return null;
            }

            try {
                numMecanografico = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Apenas podem ser inseridos numeros");
                continue;
            }

            //Passou todos os testes
            break;
        }

        while (true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.println("Escolha uma regime:");
            printMenu(menuRegime);

            infoString = sc.nextLine();

            if(infoString.equals("")) {
                return null;
            }

            try {
                opcao = Integer.parseInt(infoString);
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            try {
                regime = menuRegime[opcao];
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Opcao invalida");
                continue;
            }

            //Passou todos os testes
            break;
        }

        while (true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.println("Curso: ");

            curso = escolhaListaCursosComDisciplina(disciplina);

            if(curso == null) {
                continue;
            }

            //Passou todos os testes
            break;
        }

        while(true) {
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.print("Ano: ");

            infoString = sc.nextLine();

            if(infoString.equals("")) {
                return null;
            }

            try{
                ano = Integer.parseInt(infoString);
            } catch(NumberFormatException e) {
                System.out.println("Insira um ano valido");
                continue;
            }

            //Passou todos os testes
            break;
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

    //Criar menu de escoha com lista de todos os alunos em pessoas e devolver alunoescolhido
    private static Aluno escolhaAlunoListaPessoas(Disciplina disciplina) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        int opcao;
        ArrayList<Aluno> alunos = getAlunosPessoas();

        if(alunos == null) {
            return null;
        }

        for(Aluno aluno : alunos) {
            System.out.println(aluno.toStringDetailed());

            ++i;
        }

        System.out.println("[" + i + "] Criar aluno");
        System.out.print("Opcao: ");

        try {
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida");
            return null;
        }

        try {
            return alunos.get(opcao);
        } catch(ArrayIndexOutOfBoundsException e) {
            //ultima opcao
            if(opcao == alunos.size()) {
                return criarAluno(disciplina);
            }
            System.out.println("Opcao invalida");
            return null;
        }
    }

    //Guardas dados em ficheiro
    private static void guardarDados() throws IOException {
        FileWriter cursosFile =  new FileWriter("cursos.txt");
        for(Curso curso : cursos) {
            cursosFile.write(curso.toString());
            System.out.printf(curso.toString());
        }
        cursosFile.close();

        FileWriter pessoasFile =  new FileWriter("pessoas.txt");
        for(Pessoa pessoa : pessoas) {
            pessoasFile.write(pessoa.toString());
            System.out.printf(pessoa.toString());
        }
        pessoasFile.close();

        FileWriter examesFile =  new FileWriter("exames.txt");
        for(Exame exame : exames) {
            examesFile.write(exame.toString());
            System.out.printf(exame.toString());
        }
        examesFile.close();

        System.out.printf("Data saved at Database.db");
    }

    public void guardarHistorico() {

    }

    /*
    private static void carregarDados() throws IOException {
        FileReader cursosFile = new FileReader("cursos.txt");
        BufferedReader cursosBuffer = new BufferedReader(cursosFile);
        String line;
        Curso curso = null;
        String nomeCurso = "Erro";
        int duracaoCurso = -1;
        String grauConfereCurso = "Erro";
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        String nomeDisciplina = "Erro";
        int numResponsavel;
        FuncionarioDocente responsavelDisciplina;
        String[] catcher;
        int a = 0;
        ArrayList<FuncionarioDocente> outrosDocentes;

        while ((line = cursosBuffer.readLine()) != null) {
            if(line.charAt(0) != '\t') {
                if(a>0) {
                    //TODO -> Continue
                    disciplinas.add( new Disciplina(nomeDisciplina, responsavelDisciplina, outrosDocentes).inscreverAluno(new Aluno()) );
                    cursos.add( new Curso(nomeCurso, duracaoCurso, grauConfereCurso, disciplinas) );
                    disciplinas = new ArrayList<>();
                }
                catcher = line.split("|");
                nomeCurso = catcher[0];
                duracaoCurso = Integer.parseInt(catcher[1]);
                grauConfereCurso = catcher[2];
                ++a;
            } else if(line.charAt(0) == '\t' && line.charAt(1) != '\t') {
                catcher = line.split("|");
                nomeDisciplina = catcher[0];
                numResponsavel = Integer.parseInt(catcher[1]);
                //TODO -> obter resposavel com o numero
            } else if(line.charAt(0) == '\t' && line.charAt(1) == '\t') {
                catcher = line.split("|");

            }

            System.out.println(line);
        }

        cursosFile.close();
    }
    */

    private static void sair() {
        System.exit(0);
    }

    private static int menu() {
        Scanner sc = new Scanner(System.in);
        Aluno auxAluno;
        Exame auxExame;
        Funcionario auxFuncionario;
        int auxInt;

        System.out.println("--- Gestor de Exames ---" + "\n");
        printMenu(menuPrincipal);
        System.out.print("\nOpcao: ");

        try {
            auxInt = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.printf("Opcao invalida");
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
                    System.out.printf("Opcao invalida");
                    return 1;
                }

                switch (auxInt) {
                    //Listar exames
                    case 0:
                        System.out.println("\n--- Listar Exames ---");
                        if(examesDisponiveis()){
                            listarExames();
                        }
                        break;
                        
                    //Listar alunos inscritos num exame
                    case 1:
                        System.out.println("\n--- Listar alunos inscritos num exame ---");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();

                            if(auxExame != null) {
                                auxExame.listarAlunosInscritos();
                            }
                        }

                        break;
                        
                    //Listar exames de um aluno
                    case 2:
                        System.out.println("\n--- Listar exames de um aluno ---");

                        if(examesDisponiveis()) {
                            auxAluno = escolhaListaAlunosInscritosExame();

                            if(auxAluno != null) {
                                auxAluno.listarExames(exames);
                            }
                        }

                        break;

                    //Listar funcionarios de um exame
                    case 3:
                        System.out.println("---Listar funcionarios de um exame---");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();

                            if(auxExame != null) {
                                auxExame.listarFuncionarios();
                            }
                        }

                        break;
                    
                    case 4:
                        System.out.println("---Listar exames de um funcionario---");

                        if(examesDisponiveis()) {
                            auxFuncionario = escolhaListaFuncionarios();

                            if(auxFuncionario != null) {
                                auxFuncionario.listarExames(exames);
                            }
                        }

                        break;
                        
                    case 5:
                        System.out.println("---Listar notas---");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();
                            if(auxExame != null) {
                                auxExame.listarNotas();
                            }
                        }

                        break;

                    case 6:
                        break;

                    default:
                        System.out.println("Opcao invalida");
                        break;
                }
                break;
            //Criar exames
            case 1:
                break;
            //Configurar exames
            case 2:
                System.out.println("--- Configurar exame ---");
                if(examesDisponiveis()) {
                    auxExame = escolhaListaExames();
                    if(auxExame != null) {
                        printMenu(menuConfigurarExame);
                        System.out.println("[3] Menu principal");
                        System.out.print("\nOpcao: ");

                        try {
                            auxInt = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.printf("Opcao invalida");
                            break;
                        }

                        switch (auxInt) {
                            //Associar vigilante
                            case 0:
                                System.out.println("--- Associar vigilante ---");
                                //Escolher funcionario ou criar um
                                auxFuncionario = escolhaFuncionarioDocenteListaPessoas();
                                if(auxFuncionario != null) {
                                    //Se conseg
                                    auxExame.inserirDocente((FuncionarioDocente)auxFuncionario);
                                }
                                break;
                            //Associar assistente
                            case 1:
                                System.out.println("--- Associar assistente ---");
                                auxFuncionario = escolhaFuncionarioNaoDocenteListaPessoas();
                                if(auxFuncionario != null) {
                                    auxExame.inserirAssistente((FuncionarioNaoDocente)auxFuncionario);
                                }
                                break;
                            //Associar aluno
                            case 2:
                                System.out.println("--- Associar aluno ---");
                                auxAluno = escolhaAlunoListaPessoas(auxExame.getDisciplina());
                                if(auxAluno != null) {
                                    if(auxAluno.getCurso().getNome().equals("Nao existente")) {
                                        System.out.println("Nao existem cursos com a disciplina");
                                    }
                                    auxExame.inscreverAluno(auxAluno);
                                }
                                break;
                            default:
                                System.out.printf("Opcao invalida");
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
                        System.out.println("Notas lançadas");
                    }
                }
                break;
            //Sair
            case 4:
                return 0;
            //Opcao invalida
            default:
                System.out.println("Opcao invalida");
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
