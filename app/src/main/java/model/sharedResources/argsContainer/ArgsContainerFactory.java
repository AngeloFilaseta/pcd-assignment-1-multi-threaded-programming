package model.sharedResources.argsContainer;

public class ArgsContainerFactory {

    private static final int N_ARGS = 3;

    public static ArgsContainer getArgsContainer(final String[] args) throws NumberFormatException, IllegalStateException {
        if(args.length == N_ARGS){
            int n = Integer.parseInt(args[0]);
            if(args[1] == null || args[2] == null){
                throw new IllegalStateException();
            }
            return new ArgsContainerImpl(n, args[2], args[1]);
        } else {
            throw new IllegalStateException();
        }
    }

}
