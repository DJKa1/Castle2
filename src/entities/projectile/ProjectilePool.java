package entities.projectile;

public class ProjectilePool {

    private Projectile[] pool;



    public ProjectilePool(){
        pool=new Projectile[256];




    }








    public void addProjectile(Projectile projectile){
        for (int i=0;i<pool.length;i++){
            if(pool[i]==null){
                pool[i]=projectile;
                System.out.println("working slot" + i );
                return;
            }
        }
        System.out.println("keine freien Projectile slots");


    }
}
