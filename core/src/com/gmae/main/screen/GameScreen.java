package com.gmae.main.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gmae.main.handlers.Contact;
import com.gmae.main.Physics_Test;
import com.gmae.main.camera.OrthoCamera;
import com.gmae.main.entity.Ground;
import com.gmae.main.entity.Platform;
import com.gmae.main.entity.Player;
import com.gmae.main.handlers.Content;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends Screen {

    //http://www.agmprojects.com/blog/setting-up-box2d-with-libgdx
    //https://www.youtube.com/playlist?list=PL-2t7SM0vDfdYJ5Pq9vxeivblbZuFvGJK

    public static final float WORLD_TO_BOX = 0.01f;
    public static final float BOX_TO_WORLD = 100f;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Contact contact;

    private Player player;
    private Ground ground;
    private float tileSize;
    private BodyDef bDef;
    private FixtureDef fDef;

    //Tiled map
    private TiledMap tileMap;
    private OrthogonalTiledMapRenderer tmr;

    public static Content res;


    ArrayList<Platform> platforms = new ArrayList<Platform>();

    Random random = new Random();


    @Override
    public void create() {
        camera = new OrthoCamera();
        camera.setToOrtho(false);
        world = new World(new Vector2(0, -20), true);
        debugRenderer = new Box2DDebugRenderer();
        contact = new Contact();
        bDef = new BodyDef();
        fDef = new FixtureDef();


        world.setContactListener(contact);

        player = new Player(new Vector2(100, 800), world, contact);
        //ground = new Ground(world);

        /*for(int i = 0; i <= 100; i++) {
            platforms.add(i, new Platform(new Vector2(random.nextInt(Physics_Test.WIDTH * 4), random.nextInt(Physics_Test.HEIGHT * 4)), world, 60, 20, contact));
        }*/

        Loadmap("Map.tmx", "ForGround");
        Loadmap("Map.tmx", "Platforms");

        //res = new Content();
        //res.loadTexture("Player.png","player");
    }

    private void Loadmap(String map, String layers){
        tileMap = new TmxMapLoader().load(map);
        tmr = new OrthogonalTiledMapRenderer(tileMap);

        TiledMapTileLayer layer = (TiledMapTileLayer)  tileMap.getLayers().get(layers);

        tileSize = layer.getTileWidth();

        for(int row = 0; row < layer.getHeight(); row++){
            for(int col = 0; col < layer.getWidth(); col++){

                TiledMapTileLayer.Cell cell = layer.getCell(col,row);

                if(cell==null) continue;
                if(cell.getTile() == null) continue;

                bDef.type = BodyDef.BodyType.StaticBody;
                bDef.position.set((col + 0.5f) * tileSize * WORLD_TO_BOX, (row + 0.5f) * tileSize * WORLD_TO_BOX);

                ChainShape cs = new ChainShape();
                Vector2[] v = new Vector2[5];

                /*Object property = cell.getTile().getProperties().get("Half_Platform");
                if(property != null){
                    //System.out.println(tileSize/2 + " " + -tileSize/2 + " " + tileSize/4 + " " + -tileSize/4);
                    v[0] = new Vector2(-tileSize / 2 * WORLD_TO_BOX, (-tileSize / 4 * WORLD_TO_BOX) );
                    v[1] = new Vector2(-tileSize / 2 * WORLD_TO_BOX, (tileSize / 4 * WORLD_TO_BOX) );
                    v[2] = new Vector2(tileSize / 2 * WORLD_TO_BOX, (tileSize / 4 * WORLD_TO_BOX) );
                    v[3] = new Vector2(tileSize / 2 * WORLD_TO_BOX, (-tileSize / 4 * WORLD_TO_BOX));
                    v[4] = new Vector2(-tileSize / 2 * WORLD_TO_BOX, (-tileSize / 4 * WORLD_TO_BOX));
                } else {*/
                    v[0] = new Vector2(-tileSize / 2 * WORLD_TO_BOX, -tileSize / 2 * WORLD_TO_BOX);
                    v[1] = new Vector2(-tileSize / 2 * WORLD_TO_BOX, tileSize / 2 * WORLD_TO_BOX);
                    v[2] = new Vector2(tileSize / 2 * WORLD_TO_BOX, tileSize / 2 * WORLD_TO_BOX);
                    v[3] = new Vector2(tileSize / 2 * WORLD_TO_BOX, -tileSize / 2 * WORLD_TO_BOX);
                    v[4] = new Vector2(-tileSize / 2 * WORLD_TO_BOX, -tileSize / 2 * WORLD_TO_BOX);
                //}
                cs.createChain(v);
                fDef.friction = 0.4f;
                fDef.shape = cs;
                fDef.isSensor = false;
                world.createBody(bDef).createFixture(fDef).setUserData("World_Tile");
            }
        }
    }

    @Override
    public void update() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // physics updates

        if(player.getPosition().y>Physics_Test.HEIGHT/1.5) {
            camera.position.set(player.getPosition().x,player.getPosition().y-Physics_Test.HEIGHT/6, 0);
        } else {
            camera.position.set(player.getPosition().x,Physics_Test.HEIGHT/2,0);
        }
        if(player.getPosition().y < - 10){
            player.setPos(100,800);
        }
        player.update();
        camera.update();
        //Matrix4 cameraCopy = camera.combined.cpy();
        //debugRenderer.render(world, cameraCopy.scl(BOX_TO_WORLD));


        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        tmr.setView(camera);
        tmr.render();
        //ground.render(sb);
        Matrix4 cameraCopy = camera.combined.cpy();
        debugRenderer.render(world,cameraCopy.scl(BOX_TO_WORLD));
        sb.end();
        player.render(sb);
        //sb.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {

    }
}
