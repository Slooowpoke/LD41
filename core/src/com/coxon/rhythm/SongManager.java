package com.coxon.rhythm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.coxon.rhythm.entities.Enemy;
import com.coxon.rhythm.entities.Note;

import java.util.ArrayList;
import java.util.Random;

public class SongManager {

    Music music;
    ArrayList<Note> notes = new ArrayList<Note>();
    ArrayList<Note> listOfNotes = new ArrayList<Note>();
    Vector2 position;
    double lastNoteSpawnTime;
    int bpm = 123;
    double crotchet = 60f / bpm;
    double lastBeat = 0;
    double lastSongPosition;
    double timer;
    Random random = new Random();
    ArrayList<Note> attackNotes = new ArrayList<Note>();
    ArrayList<Enemy> visibleEnemies = new ArrayList<Enemy>();

    SongManager(Vector2 position) {
        this.position = new Vector2(position.x, position.y - 200);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/galaxies.mp3"));

        music.play();
    }

    public void update(double delta, Vector2 playerPosition, ArrayList<Enemy> visibleEnemies) {
        this.visibleEnemies = visibleEnemies;
        if (getSongPosition() > lastBeat + crotchet) {
            System.out.println("BAM " + lastBeat);
            addRandomNote(lastBeat);
            lastBeat += crotchet;
        }

        for (Note note : notes) {
            note.update(delta, getSongPosition(), playerPosition);
        }
        for (Note note : attackNotes) {
            note.update(delta, getSongPosition(), playerPosition);
        }
        notes.removeAll(getNotesDone());
    }

    public boolean checkForNote(String keyPressedType){
        // Check the key pressed type for any notes that just ended
        ArrayList<Note> notesReady = getNotesReady();
        for(Note note: notesReady){
            if(keyPressedType.equals(note.getType())){
                // Success!
                note.setHitSuccess(true);
                note.setAttack(pickRandomVisibleEnemy(visibleEnemies));
                attackNotes.add(note);

                System.out.println("Success");
                return true;
            }
        }
        return false;
    }

    public Enemy pickRandomVisibleEnemy(ArrayList<Enemy> enemies){
        return enemies.get(random.nextInt(enemies.size()));
    }

    public ArrayList<Note> getNotesReady(){
        ArrayList<Note> notesReady = new ArrayList<Note>();
        for(Note note: notes){
            if(note.isReadyToBeHit()){
                notesReady.add(note);
            }
        }
        return notesReady;
    }

    public ArrayList<Note> getNotesDone(){
        ArrayList<Note> notesToRemove = new ArrayList<Note>();
        for(Note note: notes){
            if(note.getHitSuccess()){
                notesToRemove.add(note);
            }else if(note.getNoteMissed()){
                System.out.println("Misseed");
                notesToRemove.add(note);
            }
        }
        return notesToRemove;
    }

    private void addRandomNote(double lastBeat) {
        int rand = random.nextInt(3);
        if(rand == 0){
            addNote("LeftNote", lastBeat);
        }else if(rand == 1){
            addNote("DownNote", lastBeat);
        }else if(rand == 2){
            addNote("RightNote", lastBeat);
        }else if(rand == 3){
            addNote("UpVote", lastBeat);
        }
    }

    public void addNote(String type, double hitNoteSecond) {
        Vector2 notePosition = new Vector2(position.x, position.y);
        notes.add(new Note(notePosition, hitNoteSecond, type));
    }

    public void render(SpriteBatch batch) {
        // if any notes are hit

        for (Note note : notes) {
            note.render(batch);
        }

        for (Note note : attackNotes) {
            note.render(batch);
        }
    }

    public double getSongPosition() {
        return music.getPosition();
    }

}
