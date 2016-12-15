package com.company;

import java.io.*;
import java.util.*;

import static java.lang.Character.isLetter;

//TODO -> guardar salas

public class Gestor {
    private static ArrayList<Exame> exames = new ArrayList<>();
    private static ArrayList<Pessoa> pessoas = new ArrayList<>();
    private static ArrayList<Curso> cursos = new ArrayList<>();
    private static ArrayList<Sala> salas = new ArrayList<>();
    private ArrayList<String> historico = new ArrayList<>();
    private String importFileName;
    private String exportFileName;
    private static String[] menuPrincipal = {"Listar", "Criar exames", "Configurar exames", "Lancar notas" , "Sair"};
    private static String[] menuListar = {"Listar exames", "Listar alunos inscritos num exame", "Listar exames de um aluno", "Listar funcionarios de um exame", "Listar exames de um funcionario", "Listar notas"};
    private static String[] menuConfigurarExame = {"Editar vigilantes", "Editar assistentes", "Editar aluno"};

    public static void main(String[] args) {
        FuncionarioDocente resp = new FuncionarioDocente("Responsavel", "responsavel@domain", 1, "assistente", "sistemas de informação");
        pessoas.add(resp);
        FuncionarioDocente vig = new FuncionarioDocente("Vigilante", "vigilante@domain", 2, "auxiliar,", "comunicação e telemática");
        pessoas.add(vig);
        ArrayList<FuncionarioDocente> outrosDocentes = new ArrayList<>();
        outrosDocentes.add(vig);

        Disciplina d1 = new Disciplina("Disciplina 1", resp, outrosDocentes);
        Disciplina d2 = new Disciplina("Disciplina 2", resp, outrosDocentes);

        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(d1);
        disciplinas.add(d2);

        Sala s1 = new Sala("Sala1");
        salas.add(s1);
        Calendar date1 = Calendar.getInstance();
        date1.set(2017, 1, 5, 10, 30);
        Calendar date2 = Calendar.getInstance();
        date2.set(2017, 1, 20, 14, 0);
        Calendar date3 = Calendar.getInstance();
        date3.set(2017, 2, 5, 17, 30);
        IntervaloTempo t1 = new IntervaloTempo(date1, 30);
        IntervaloTempo t2 = new IntervaloTempo(date2, 60);
        IntervaloTempo t3 = new IntervaloTempo(date3, 60);

        Exame en = new ExameNormalRecurso(d1, s1, resp, t1, "Normal");
        exames.add(en);
        Exame er = new ExameNormalRecurso(d1, s1, resp, t2, "Recurso");
        exames.add(er);
        Exame ee = new ExameEspecial(d1, s1, resp, t3);
        exames.add(ee);

        FuncionarioNaoDocente ass = new FuncionarioNaoDocente("Assistente", "assistente@domain", 3, "assistente operacional", "secretaria");
        pessoas.add(ass);

        Curso c1 = new Curso("Curso 1", 3, "Licenciatura", disciplinas);
        cursos.add(c1);

        Aluno a1 = new Aluno("Aluno 1", "aluno1@domain", 1000, c1, 1, "normal");
        pessoas.add(a1);

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
            System.out.println(exame.toStringDetailed());
        }

        System.out.println("");
    }

    //Criar menu de escolha com a lista de todos os exames e devolver o exame escolhido
    private static Exame escolhaListaExames() {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);

        //Imprimir exames
        for (Exame exame : exames) {
            System.out.printf("[%d] %s\n", i, exame.toStringDetailed());
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
                repeat = true;
            }

            //Passou todos os testes
            if(!repeat) {
                break;
            }
        }

        while(true) {
            repeat = false;
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
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.println("Escolha uma categoria:");
            printMenu(menuCategoria);

            System.out.print("\nOpcao: ");

            infoString = sc.nextLine();

            if(infoString.equals("")) {
                return null;
            }

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
            System.out.println("Para voltar ao menu principal envie uma resposta vazia");
            System.out.println("Escolha uma area de investigacao:");
            printMenu(menuAreaInvestigacao);

            System.out.print("\nOpcao: ");

            infoString = sc.nextLine();

            if(infoString.equals("")) {
                return null;
            }

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
        } catch(IndexOutOfBoundsException e) {
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
            System.out.println("[" + i + "] " + funcionarioNaoDocente.toStringDetailed());

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

        if(cursos.size() == 0) {
            return new Curso();
        }

        for(Curso curso : cursos) {
            System.out.println("[" + i + "] " + curso.toStringDetailed());
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
            System.out.println("[" + i + "] " + aluno.toStringDetailed());

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
                                System.out.println("--- Associar vigilante --\n-");
                                //Escolher funcionario ou criar um
                                auxFuncionario = escolhaFuncionarioDocenteListaPessoas();
                                if(auxFuncionario != null) {
                                    //Se conseg
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
                        System.out.println("Notas lançadas");
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
