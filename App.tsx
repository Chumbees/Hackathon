import React from 'react';
import {StyleSheet, Text, View, Button} from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>

      {/* Example of colored view and text */}
      <View style={{backgroundColor: 'blue'}}>
        <Text style={{color: '#fff'}}>White text blue box</Text>
      </View>

      {/* Uncolored text */}
      <Text>If you can read this you did it right. -Chumbees</Text>

      {/* Example button */}
      <Button
        title='example button'
        // onPress={}
        />

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
