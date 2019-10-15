import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import CardsContainer from './sections/CardsContainer';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>PLAN YO SHIT</Text>
      <CardsContainer />
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
