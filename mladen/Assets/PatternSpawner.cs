using UnityEngine;
using System.Collections;

public class PatternSpawner : MonoBehaviour {

	public GameObject[] primitives;
	public Transform spawnPos;

	private GameObject[] objectsForPattern;
	private GameObject lastActivated;

	public Vector2 range;
	public Rigidbody rigidbody;
	private PlatformSpawner controller;

	void Start() {
		objectsForPattern = new GameObject[10];
		loadRandomPrimitives();
		activatePatternUp();
		rigidbody = GameObject.FindGameObjectWithTag("Player").GetComponent<Rigidbody>();
		controller =  GameObject.FindGameObjectWithTag ("GameController").GetComponent<PlatformSpawner> ();

	}
	public bool isLocked;
	void Update() {
		if (Input.GetKey(KeyCode.Return))
			Application.LoadLevel(Application.loadedLevel);
		//0.3f rigid v x;
		int direction = 0;
		if (rigidbody.velocity.x > 0.25f) direction = 1;
		if (rigidbody.velocity.x < -0.25f) direction = -1;
		if (!controller.isGameOver){
			if (lastActivated == null && !isLocked) {
				isLocked = true;
				switch (direction) {
					case 0: activatePatternUp(); break;
					case 1: activatePatternRight(); break;
					case -1: activatePatternLeft(); break;
			    }
				controller.spawnedPatterns++;
			}
			else if (lastActivated != null) isLocked = false;
		}
		loadRandomPrimitives();
	}

	void loadRandomPrimitives() {
		for (int i = 0; i < objectsForPattern.Length; i++) {
			objectsForPattern[i] = primitives[Random.Range(0,primitives.Length)];
		}
	}
	void activatePatternUp() { 
	   StartCoroutine(instantiateAfterSeconds(0.0f, objectsForPattern[0], new Vector3(0f,0f,0f), Quaternion.identity));
       StartCoroutine(instantiateAfterSeconds(0.2f, objectsForPattern[1], new Vector3(0f,0f,2f), Quaternion.identity));
       StartCoroutine(instantiateAfterSeconds(0.4f, objectsForPattern[2], new Vector3(0f,0f,4f), Quaternion.identity));

    }
	void activatePatternRight() {
		StartCoroutine(instantiateAfterSeconds(0.0f, objectsForPattern[0], new Vector3(0f,0f,0f), Quaternion.identity));
		StartCoroutine(instantiateAfterSeconds(0.2f, objectsForPattern[1], new Vector3(0f,0f,2f), Quaternion.identity));
		StartCoroutine(instantiateAfterSeconds(0.4f, objectsForPattern[2], new Vector3(2f,0f,2f), Quaternion.identity));
	}
	void activatePatternLeft() {
		StartCoroutine(instantiateAfterSeconds(0.0f, objectsForPattern[0], new Vector3(0f,0f,0f), Quaternion.identity));
		StartCoroutine(instantiateAfterSeconds(0.2f, objectsForPattern[1], new Vector3(0f,0f,2f), Quaternion.identity));
		StartCoroutine(instantiateAfterSeconds(0.4f, objectsForPattern[2], new Vector3(-2f,0f,2f), Quaternion.identity));
	}

	IEnumerator instantiateAfterSeconds(float seconds, GameObject obj,Vector3 pos, Quaternion rotation)
	{
		yield return new WaitForSeconds(seconds);
		lastActivated = (GameObject) Instantiate(obj,spawnPos.position + pos,rotation);

	}

}
