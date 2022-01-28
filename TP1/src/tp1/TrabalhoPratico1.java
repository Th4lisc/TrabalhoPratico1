package tp1;

import java.util.Scanner;
import java.util.Random;

public class TrabalhoPratico1 {

	// Variaveis globais
	static int cont_tema = 0;
	static String tabela[][] = new String[51][51];
	
	public static void main (String[] args) {
		
		int opcao = 0;
		
		preencheTabela();
		
		do {
			menu();	// Printa o menu
			opcao = opcao_validada();
			switchCase(opcao);
		} while (opcao != 4);
				
	}
	
	static void menu () {									// Printa o menu
		System.out.println("===============================");
		System.out.println("Jogo da Forca");
		System.out.println("===============================");
		System.out.println("1. Gerenciar Temas");
		System.out.println("2. Gerenciar Palavras");
		System.out.println("3. Jogar");
		System.out.println("4. Sair");
		System.out.println("===============================");
	}
	
	static int opcao_validada () {							// Lê e valida a opcao escolhida
		boolean k;
		int opcao = 0;
		Scanner leia = new Scanner(System.in);
		
		try {
			opcao = leia.nextInt();	// Lê a opção
			k = true;
		} catch (java.util.InputMismatchException e) {		// Trata algum caracter indevido
			System.out.println("Entrada inválida, reinicie o programa");
			k = false;
		}
		
		if(k) {				// Retorna a opcao se for validada
			return opcao;
		} else {			// Retorna 0 se for invalida
			return 0;
		}
	}
	
	static String palavra_validada () {						// Lê e valida a palavra digitada
		boolean k;
		String palavra = "";
		Scanner leia = new Scanner(System.in);
		
		try {
			palavra = leia.nextLine();						// Lê a linha
			k = true;
		} catch (java.util.InputMismatchException e) {		// Trata algum caracter indevido
			System.out.println("Entrada inválida");
			k = false;
		}
		
		if(k) {												// Retorna a palavra se for validada
			return palavra;
		} else {											// Retorna 0 se for invalida
			return "";
		}
	}
	
	static void switchCase (int opcao) {					// Switch Case
				
		switch (opcao) {
			case 1:
				limpaTela();
				System.out.println("======= Gerenciar Temas =======");
				System.out.println("1. Cadastro");
				System.out.println("2. Exclusão");
				System.out.println("3. Busca");
				System.out.println("===============================");
				
				gerenciarTemas(opcao_validada());
				
				break;
				
			case 2:
				limpaTela();
				System.out.println("======= Gerenciar Palavras =======");
				System.out.println("1. Cadastro");
				System.out.println("2. Exclusão");
				System.out.println("3. Busca");
				System.out.println("4. Listagem");
				System.out.println("==================================");
				
				gerenciarPalavras(opcao_validada());
				
				break;
				
			case 3:
				
				boolean jogarnovamente;
				
				do {
					
					String tema_jogar = escolheTema();

					
					if (tema_jogar.equals("")) {
						System.out.println("Tema não escolhido, escolha um tema para jogar");
						aperteEnter();
						break;
					} else {
						limpaTela();
						System.out.println("======== Jogo da Forca ========");
						
						jogar(tema_jogar);
						jogarnovamente = jogarNovamente();
					}
					
				} while (jogarnovamente);
				
				break;
				
			case 4:
				System.out.println("");
				System.out.println("Até a próxima :D");
				break;
				
			default:
				System.out.println("");
				System.out.println("Opção incorreta, digite um valor válido");
		}
		
	}
	
	static void limpaTela () {								// Pula 5 linhas
		for (int i = 0; i < 5; i++) System.out.println();
	}
	
	static void aperteEnter () {
		System.out.println();
		System.out.println("Aperte enter para continuar...");
		String c = palavra_validada();
	}
	
	static void preencheTabela () {
		for (int i = 0; i <= 50; i++) {
			for (int j = 0; j <= 50; j++) {
				tabela[i][j] = "";
			}
		}
	}
	
	static void gerenciarTemas (int opcao_1) {				// 1. Gerenciar Temas
				
		switch (opcao_1) {
			case 1:											// Cadastro
				
				boolean k = true;							// Controlador
				System.out.print("Digite um tema: ");
				String tema = palavra_validada();
				
				if (cont_tema == 0) { 						// Verifica se é o primeiro tema cadastrado
					tabela[0][0] = tema; 					// Cadastra
					System.out.println("Tema cadastrado com sucesso");
					k = false;
					cont_tema++;							// Contabiliza o tema
				} else if (cont_tema < 50) {				// Se não for o primeiro
					for(int i = 0; i <= cont_tema; i++) {	// Verifica se ja existe o tema
						if (tabela[i][0].equals(tema)) {
							System.out.println("O tema ja existe!");
							k = false;
							break;
						}
					}
				} else {									// Se chegar em 50 temas
					System.out.println("Você chegou ao limite do numero de temas");
					break;
				}
				
				if (k) {									// Se o tema não existir
					for (int i = 0; i <= cont_tema; i++) {
						if(tabela[i][0].equals("") || tabela[i][0] == null) {
							tabela[i][0] = tema;
							System.out.println("Tema cadastrado com sucesso");
							cont_tema++;
							break;
						}
					}
				}
				
				aperteEnter();
				break;
				
			case 2:											// Exclusão
				System.out.print("Digite o tema a ser excluido: ");
				String tema_excluir = palavra_validada();
				
				for (int i = 0; i <= cont_tema; i++) {		// Percorre a coluna de temas
					if (tabela[i][0].equals(tema_excluir)) {
						for (int j = 1; j <= 50; j++) {		// Percorre a linha do tema
							if ( !(tabela[i][j] == null || tabela[i][j].equals("")) ) {	// Verifica se existem palavras no tema
								System.out.println("Não foi possível excluir o tema. Verifique se existem palavras cadastradas nesse tema.");
								break;
							}
						}
						tabela[i][0] = "";					// Exclui tema
						System.out.println("Tema excluido com sucesso!");
						cont_tema--;						// Diminui o numero de temas
						break;
					} else if (i == cont_tema) {			// Se chegar no ultimo tema e não encontrar
						System.out.println("Tema não cadastrado");
					}
				}
				
				aperteEnter();
				break;
				
			case 3:											// Busca
				System.out.print("Digite o tema : ");
				String tema_busca = palavra_validada();
				
				for (int i = 0; i <= cont_tema; i++) {		// Percorre a coluna de temas
					if (tabela[i][0].equals(tema_busca)) {
						System.out.println("Tema encontrado: ");
						System.out.println(tema_busca + " - indice " + i);
						break;
					} else if (i == cont_tema) {			// Se chegar no ultimo e não tiver achado
						System.out.println("Tema não cadastrado");
					}
				}
				
				aperteEnter();
				break;
				
			default:
				System.out.println("Opção incorreta, digite um valor válido");
				aperteEnter();
		}
	}
	
	static void gerenciarPalavras (int opcao_2) {			// 2. Gerenciar Palavras

		switch (opcao_2) {
			case 1:	// Cadastro
				System.out.println("Digite um tema para cadastrar alguma palavra: ");
				String tema_palavra = palavra_validada();
				
				if (cont_tema == 0) {
					System.out.println("Nenhum tema cadastrado, cadastre um tema antes");
					break;
				} else {
					for (int i = 0; i <= cont_tema; i++) {	// Percorre procurando se o tema existe
						if (tabela[i][0].equals(tema_palavra)) {
							for (int j = 1; j <= 50; j++) {	// Percorre a linha do tema
								if (tabela[i][j] == null || tabela[i][j].equals("")) {
									System.out.println("Digite a palavra a ser cadastrada: ");
									
									String palavra = palavra_validada();
									
									if ( ! (palavra.equals(""))) {
										tabela[i][j] = palavra;
										System.out.println("Palavra '" + tabela[i][j] + "' cadastrada com sucesso");
									} else {
										System.out.println("Palavra invalida");
									}
									break;
								}
							}
							break;
						} else if (i == cont_tema) {		// Se chegar no ultimo e nao achar
							System.out.println("Tema não encontrado");
						}
					}
				}
				
				
				aperteEnter();
				break;
				
			case 2:	// Exclusão
				System.out.println("Digite a palavra a ser excluida: ");
				String palavra_excluir = palavra_validada();
				
				for (int i = 0; i <= 50; i++) {
					for (int j = 1; j <= 50; j++) {
						if (tabela[i][j].equals(palavra_excluir)) {
							tabela[i][j] = "";
							System.out.println("Palavra excluida com sucesso");
							break;
						}
					}
					if (i == 50) {
						System.out.println("Palavra não encontrada");
						break;
					}
				}
				
				aperteEnter();
				break;
				
			case 3:	// Busca
				
				boolean palvraEncontrada = false;
				
				System.out.println("Digite a palavra a ser buscada: ");
				String palavra_buscar = palavra_validada();
				
				for (int i = 0; i <= 50; i++) {
					for (int j = 1; j <= 50; j++) {
						if (tabela[i][j].equals(palavra_buscar)) {
							System.out.println("Palavra encontrada no tema " + tabela[i][0]);
							System.out.println(palavra_buscar + " - indice " + i + "." +j);
							palvraEncontrada = true;
							break;
						}
					}
				}
				
				if ( ! (palvraEncontrada)) {
					System.out.println("Palavra não encontrada");
					break;
				}
				
				aperteEnter();
				break;
			
			case 4:	// Listagem
				System.out.println("Digite o tema a ser listado: ");
				String tema_listar = palavra_validada();
				
				for (int i = 0; i <= 50; i ++) {
					if (tabela[i][0].equals(tema_listar)) {
						for (int j = 0; j <= 50; j++) {
							if (!(tabela[i][j].equals("") || tabela[i][j] == null)) {
								System.out.print(tabela[i][j+1] + " - ");
							} else {
								break;
							}
						}
						break;
					} else if (i == 50) {
						System.out.println("Tema não encontrado");
					} 
				}
				
				aperteEnter();
				break;
				
			default:
				System.out.println("Opção incorreta, digite um valor válido");
				aperteEnter();
		}	
	}
	
	static String escolheTema () {
		
		String tema_jogar;
		
		System.out.println("Digite um tema para jogar: ");
		tema_jogar = palavra_validada();
		
		for (int i = 0; i < cont_tema; i++) {
			if (tabela[i][0].equals(tema_jogar)) {			// Se achar o tema
				for (int j = 1; j < 50; j++) {
					if ( !(tabela[i][j].equals("") || tabela[i][j] == null) ) {	// Verifica se existem palavras cadastradas
						System.out.println("Tema escolhido!");
						break;								// Se existir alguma palavra cadastrada, nao precisa mais procurar
						
					} else if (j == 50) {
						System.out.println("Tema sem palavras cadastradas");
						tema_jogar = "";					// Deixa a string vazia para evitar erros
						break;
					}
				}
				
			} else if (i == cont_tema) {					// Se o tema não existir
				System.out.println("Tema não encontrado");
				tema_jogar = "";							// Deixa a string vazia para evitar erros
				break;
			}
		}
		
		return tema_jogar;
		
	}

	static void jogar (String tema_jogar) {					
		
		int erros = 0;
		int acertos = 0;
		String palavra = "";
		
		Random rand = new Random();

		for(int i = 0; i <= cont_tema; i++) {				// Procura o tema
			if (tabela[i][0].equals(tema_jogar)) {
				do {
				palavra = tabela[i][(rand.nextInt(50)) + 1];// Pega uma palavra random na linha do tema
				} while (palavra.equals(""));				// Para pegar um palavra valida e não um ""
				break;
				
			} else if (i == cont_tema){
				System.out.print("Tema não encontrado");
			}
		}
		
		do {
			System.out.println("===============================\n");
			
			for (int i = 0; i < palavra.length(); i++) {
				System.out.print("_ ");
			}
			System.out.println("\n===============================");
			System.out.println("Digite uma letra: ");
			
			String letra = palavra_validada();
			String letras_escolhidas = "";
			
			if (letras_escolhidas.contains(letra)) {
				System.out.println("Tente outra letra");
				
			} else {
				letras_escolhidas = letras_escolhidas.concat(letra);	// Add a letra na variavel de letras ja escolhidas
				
				if (palavra.contains(letra)) {
					for (int i = 0; i < palavra.length(); i++) {		// Procura a letra escolhida na palavra
						if (palavra.split("")[i].equals(letra)) {
							System.out.println("Letra correta!");
							acertos++;
						}
					}
				} else {												// Se não tiver a letra
					System.out.println("Letra errada!");
					erros++;
				}
			}
			
			System.out.print("\nQnt de letras na palavra = " + palavra.length());
			System.out.print(" | Acertos = " + acertos);
			System.out.print(" | Erros = " + erros);
			aperteEnter();
			
		} while ( ! (acertos == palavra.length() || erros == 5));
		
		if (erros == 5) {
			System.out.println("Você perdeu! Deseja jogar novamente? (s/n)");
		} else {
			System.out.println("Parabéns! Você acertou a palavra! Deseja jogar novamente? (s/n)");
		}
	}
	
	
	static boolean jogarNovamente () {
		
		String jogarnovamente = palavra_validada();
		
		if (jogarnovamente.equals("s") || jogarnovamente.equals("S")) {
			return true;
		} else {
			return false;
		}
	}
}
