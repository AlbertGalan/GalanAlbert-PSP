public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Error: No s'ha especificat la funció.");
            return;
        }

        String funcio = args[0];
        switch (funcio) {
            case "carregarPagina":
                if (args.length == 2) {
                    Carregador.carregarPagina(args[1]);
                } else {
                    System.out.println("Error: No has especificat la URL.");
                }
                break;

            case "analitzarNombreCaracteres":
                if (args.length == 2) {
                    Analitzador.analitzarNombreCaracteres(args[1].charAt(0));
                } else {
                    System.out.println("Error: No has especificat el caràcter a comptar.");
                }
                break;
            case "substituirLletra":
                if(args.length == 3){
                    Substituineitor.substituirLletra(args[1], args[2]);
                } else {
                    System.out.println("Error:  Te falta un des dos arguments,  o n'hi ha un buit");
                }
                break;
            case "llegirEncrypted":
                    Lector.llegirEncrypted();
                break;
            case "cercarParaulesClau":
                if (args.length == 2) {
                    Cercador.cercarParaula(args[1]);
                } else {
                    System.out.println("Error: Has d'introduir una paraula per cercar.");
                }
                break;
            case "crearArxiuIndex":
                CrearIndex.crearArxiuIndex();
                break;
            case "executarIndex":
                Executable.executarIndex();
                break;
            default:
                System.out.println("Funció no vàlida.");
        }
    }
}

