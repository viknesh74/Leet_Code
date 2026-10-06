/**
 * @param {Promise} p1
 * @param {Promise} p2
 * @return {Promise}
 */
var addTwoPromises = async function(p1, p2) {
    const a=await p1;
    const b=await p2;
    return a+b;
    
};

/**
 * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
 *   .then(console.log); // 4
 */