package myl.jdots.server.views;


import de.felixroske.jfxsupport.SplashScreen;


/**
 * create by maoyule on 2019/5/19
 */
public class JdotsSplash extends SplashScreen {

    public JdotsSplash(){

    }

    @Override
    public String getImagePath() {
        return "/splash.png";
    }

    /**
     * Customize if the splash screen should be visible at all
     *
     * @return true by default
     */
    @Override
    public boolean visible() {
        return true;
    }
//
//
//    @Override
//    public Parent getParent() {
//        Group gp = new Group();
//        ImageView imageView = new ImageView(this.getClass().getResource(this.getImagePath()).toExternalForm());
//        gp.getChildren().add(imageView);
//        return gp;
//    }
}
