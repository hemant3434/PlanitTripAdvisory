import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import MapPicker from './components/MapPicker';
import CardsContainer from './sections/CardsContainer';
import Calendar from './components/Calendar';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>PLAN YO SHIT</Text>
      <MapPicker />
      <CardsContainer />
      <Calendar />
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
