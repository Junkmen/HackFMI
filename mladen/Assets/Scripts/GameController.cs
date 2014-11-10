using UnityEngine;
using System.Collections;

public class GameController : MonoBehaviour {

	public Transform[] spawnPoints;
	public GameObject platform;

	public float spawnWait;
	public float distance;

	private float currentDistance;

	public int score;

	private bool gameOver;

	// Use this for initialization
	void Start () {
		score = 0;
		gameOver = false;
		currentDistance = 0;
		/*var sc_obj = GameObject.Find("Circle Pattern");
		go.transform.F*/
		//StartCoroutine( myCorountine());
		//myCorountine();
	}
	
	// Update is called once per frame
	void Update () {
		//StartCoroutine( myCorountine());

		GameObject[] platforms = GameObject.FindGameObjectsWithTag("Platform");
		if (platforms.Length < 20) {
			int random = Random.Range(0, spawnPoints.Length);
			currentDistance += distance;
			Vector3 spawnPosition = spawnPoints[random].position + new Vector3(0f, 0f, currentDistance);
			Quaternion spawnRotation = Quaternion.identity;
			Instantiate(platform, spawnPosition, spawnRotation);
			//Debug.Log(transform.Find("Circle Pattern").GetComponent("CheckForTouch").score);
		}
	}


	//IEnumerator 
	/*void myCorountine(){
		while (!gameOver){
			int random = Random.Range(0,spawnPoints.Length);
			Vector3 spawnPosition = spawnPoints[random].position;
			spawnPosition.Set(spawnPosition.x,spawnPosition.y,spawnPosition.z + distance);
			Quaternion spawnRotation = Quaternion.identity;
			Instantiate (platform, spawnPosition, spawnRotation);
			//Instantiate(platform);
			//yield return new WaitForSeconds(spawnWait);
		}
	}*/

}


