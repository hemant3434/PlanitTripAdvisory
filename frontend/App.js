import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import HybridApp from './src/App';

export default function App() {
  return (
    <HybridApp />
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
