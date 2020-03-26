package entities.projectile;

public class ProjectilePool {

    private Projectile[] pool;



    public ProjectilePool(){
        pool=new Projectile[256];




    }




    public void addProjectile(Projectile projectile){
        for (Projectile p:pool){
            if(p==null){
                p=projectile;
                //System.out.println("working");
                return;
            }
        }
       // System.out.println("keine freien Projectile slots");


    }
}
