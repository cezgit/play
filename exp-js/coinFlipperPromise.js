console.log("I'm about to flip a coin!")

const flip = () => {  
    let x = (Math.floor(Math.random() * 2) == 0)
    if(x){
    	return "heads"
    }else{
        return "tails"
    }
}

// const resolve = () => {
// 	console.log("resovling...")
// }

const coinFlipper = () => {
	return new Promise((resolve, reject) => {
	  console.log("I'm flipping the coin!")
	  
	  const flipResult = flip() //let's say flip() takes a few seconds
	  
	  if(flipResult) {
	    // console.log("Here is the coin flip result!", flipResult)
	    resolve(flipResult)
	  } else {
	    reject()
	  }
	})
}

console.log("Wait, did I flip the coin?")

coinFlipper().then((result) => {
	console.log(`resolved: I have flipped the coin. The result was: ${result}`)	
}).then(() => {
	console.log("Did you guess the result?")
})
.finally(() => {
	console.log("DONE!")	
});



