using UnityEngine;
using System.Collections;

public class CheckForTouch : MonoBehaviour {
	Vector3 inputPos;
	Camera camera;

	//static public int score;

	static public int counter_pressed;
	private PlatformSpawner controller;



	public GameObject particlePrefab;
	void Start() {
		inputPos = new Vector3(0f,0f,0f);
		camera = GameObject.FindGameObjectWithTag("MainCamera").GetComponent<Camera>();
		controller =  GameObject.FindGameObjectWithTag ("GameController").GetComponent<PlatformSpawner> ();
	
	}
	void Update () {
		if (Input.touchCount > 0) {
			for (int i = 0; i < Input.touchCount; i++) {
				Vector3 touch =new Vector3(Input.touches[i].position.x,transform.position.y,Input.touches[i].position.y);
				touch = Camera.main.WorldToScreenPoint(touch);
				if (Vector3.Distance(transform.position,touch) < 2f) {
					Instantiate(particlePrefab,transform.position,Quaternion.identity);
					counter_pressed++;
					controller.score = counter_pressed / 3;
					Destroy (gameObject);
				}
			}
		}

		if (Input.GetMouseButton(0)) {
			inputPos = Input.mousePosition;
			inputPos = camera.ScreenToWorldPoint(inputPos);
			inputPos.Set(inputPos.x,transform.position.y,inputPos.z);
		
			if (Vector3.Distance(transform.position,inputPos) < 1f) {
				Instantiate(particlePrefab,transform.position,Quaternion.identity);

				transform.localScale = new Vector3(0f,0f,0f);
				counter_pressed++;
				controller.score = counter_pressed / 3;
				Debug.Log(counter_pressed);
				//Debug.Log(score);
				Destroy (gameObject);
			}
		}

		//guiText.text = "SCORE: " + controller.score;
		if (controller.score * 3 + 10 < counter_pressed) {
			Debug.Break();	
		}
	}
}
