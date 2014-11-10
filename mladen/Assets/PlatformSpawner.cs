using UnityEngine;
using System.Collections;

public class PlatformSpawner : MonoBehaviour {

	public GameObject platform;
	public float spaceBetween;
	public float minX,maxX;

	private Vector3 platformPos;
	private GameObject prevPlatform;

	public GUIText gameOverText;
	public GUIText scoreText;

	public int score;//destroyed
	public int spawnedPatterns;

	public	bool isGameOver;

	void Start () {
		gameOverText.text = "Game Over!!!";
		gameOverText.enabled = false;
		scoreText.text = "SCORE: " + score;
		spawnedPatterns = 0;
		isGameOver = false;
		score = 0;
		platformPos = GameObject.FindGameObjectWithTag("Player").GetComponent<Transform>().position;
		platformPos = new Vector3(platformPos.x,platformPos.y,platformPos.z);
		platformPos.Set (platformPos.x,platformPos.y,platformPos.z - 1f);
		prevPlatform = Instantiate(platform,platformPos,Quaternion.identity) as GameObject;
		platformPos.Set(Random.Range(minX,maxX),platformPos.y,platformPos.z+spaceBetween);

		while (prevPlatform.GetComponent<Collector>().getRenderer().isVisible) addPlatform();
		GameObject.FindGameObjectWithTag("Player").GetComponent<JumpTo>().setTarget(prevPlatform.transform);

	}

	void addPlatform() {
		GameObject currPlatform = Instantiate(platform,platformPos,Quaternion.identity) as GameObject;
		prevPlatform.GetComponent<RedirectToNextPlatform>().setTarget(currPlatform.GetComponent<Transform>());
		prevPlatform = currPlatform;
		platformPos.Set(Random.Range(minX,maxX),platformPos.y,platformPos.z+spaceBetween);
	}

	void Update () {
		if(Input.GetMouseButtonDown(0))
			audio.Play();

		if (score + 3  <= spawnedPatterns  )
			gameOver();
		if (!isGameOver)
			if (prevPlatform.GetComponent<Collector>().getRenderer().isVisible) addPlatform();
		
		scoreText.text = "SCORE: " + score;
	}

	public void gameOver (){
		isGameOver = true;
		gameOverText.enabled = true;
		Rigidbody rig = GameObject.FindGameObjectWithTag("Player").GetComponent<Rigidbody>();
		JumpTo temp = GameObject.FindGameObjectWithTag("Player").GetComponent<JumpTo>();
		rig.velocity = transform.forward * 10 + new Vector3(-5f, 0f, 0f);//temp.transform.forward*10f + new Vector3(temp.target.position.x - temp.transform.position.x,0f,0f)/1.9f;
	}

}
